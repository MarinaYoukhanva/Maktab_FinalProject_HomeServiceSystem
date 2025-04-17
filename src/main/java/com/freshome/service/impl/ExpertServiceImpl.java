package com.freshome.service.impl;

import com.freshome.dto.ChangePasswordDTO;
import com.freshome.dto.credit.CreditResponseDTO;
import com.freshome.dto.subService.SubServiceResponseDTO;
import com.freshome.entity.*;
import com.freshome.dto.credit.CreditCreateDTO;
import com.freshome.dto.expert.ExpertCreatDTO;
import com.freshome.dto.expert.ExpertResponseDTO;
import com.freshome.dto.expert.ExpertUpdateDTO;
import com.freshome.entity.entityMapper.CreditMapper;
import com.freshome.entity.entityMapper.ExpertMapper;
import com.freshome.entity.entityMapper.SubServiceMapper;
import com.freshome.entity.enumeration.UserStatus;
import com.freshome.exception.ExistenceException;
import com.freshome.exception.NotApprovedUserException;
import com.freshome.exception.NotFoundException;
import com.freshome.exception.NotPendingApprovalExpertException;
import com.freshome.repository.ExpertRepository;
import com.freshome.service.*;
import com.freshome.service.specification.ExpertSpecification;
import com.freshome.service.verification.ExpertVerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpertServiceImpl implements ExpertService {

    private final ExpertRepository expertRepository;
    private final CreditService creditService;
    private final SubServiceService subServiceService;
    private final UserService userService;
    private final RoleService roleService;
    private final ExpertVerificationService expertVerificationService;

    @Override
    @Transactional
    public ExpertResponseDTO createExpert(ExpertCreatDTO expertCreatDTO) throws IOException {
        log.info("create expert in service triggered");

        Role role = roleService.findByName(Role.EXPERT);
        Expert expert = ExpertMapper.expertFromDto(expertCreatDTO);
        expert.getUser().getRoles().add(role);

        if (expertRepository.existsByPhoneNumber(expertCreatDTO.getPhoneNumber()))
            throw new ExistenceException("phoneNumber");

        userService.save(expert.getUser());
        Credit credit = creditService.createReturnCredit(new CreditCreateDTO(0.0));
        expert.setCredit(credit);

        Expert savedExpert = expertRepository.save(expert);
        expertVerificationService.sendVerificationEmail(expert.getUser());
        return ExpertMapper.dtoFromExpert(savedExpert);
    }

//    @Override
//    public void calculateScore(Expert expert) {
//        Double score = reviewRepository.expertScoreFromRatingsAverage(expert.getId());
//        expert.setScore(score == null ? 0.0 : score);
//    }

    @Override
    public ExpertResponseDTO findExpertById(Long id) {
        return ExpertMapper.dtoFromExpert(
                expertRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(Expert.class, id)));
    }

    @Override
    public Optional<Expert> findOptionalExpertById(Long id) {
        return expertRepository.findById(id);
    }

    @Override
    public Optional<Expert> findByUsername(String username) {
        return expertRepository.findByUser_Username(username);
    }

    @Override
    public List<Expert> findAll() {
        return expertRepository.findAll();
    }

    @Override
    public List<ExpertResponseDTO> findAllExperts() {
        return expertRepository.findAll().stream()
                .map(ExpertMapper::dtoFromExpert)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExpertResponseDTO> expertsPendingApproval() {
        return expertRepository.findByStatus(UserStatus.PENDING_APPROVAL)
                .stream()
                .map(ExpertMapper::dtoFromExpert)
                .toList();
    }

    @Override
    @Transactional
    public void approveExpert(Long id) {
        Expert expert = expertRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Expert.class, id));
        if (expert.getStatus() != UserStatus.PENDING_APPROVAL)
            throw new NotPendingApprovalExpertException();
        expert.setStatus(UserStatus.APPROVED);
        expertRepository.save(expert);
    }

    @Override
    @Transactional
    public void deleteExpertById(Long id) {
        Expert expert = expertRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Expert.class, id));
        userService.removeRoleAndDeleteUserIfEmptyRoles(expert.getUser(), Role.EXPERT);
        expertRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ExpertResponseDTO updateExpert(ExpertUpdateDTO updateDTO) {
        Expert expert = expertRepository.findById(updateDTO.getId())
                .orElseThrow(() -> new NotFoundException(Expert.class, updateDTO.getId()));
        updateFields(expert, updateDTO);
        userService.update(expert.getUser(), updateDTO);
        return ExpertMapper.dtoFromExpert(
                expertRepository.save(expert));
    }

    @Override
    public List<ExpertResponseDTO> searchExpert(
            List<String> fields, List<String> values,
            String expertise, Double minScore, Double maxScore
    ) {
        return expertRepository.findAll(
                        ExpertSpecification.searchExpert(
                                fields, values, expertise, minScore, maxScore))
                .stream()
                .map(ExpertMapper::dtoFromExpert)
                .toList();
    }

    @Override
    @Transactional
    public void changePassword(String username, ChangePasswordDTO dto) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(Expert.class, username));
        userService.changePassword(
                user, dto.oldPassword(), dto.newPassword()
        );
    }

    @Override
    public CreditResponseDTO findCreditForExpert(String username) {
        Expert expert = expertRepository.findByUser_Username(username)
                .orElseThrow(()-> new NotFoundException(Expert.class, username));
        return CreditMapper.dtoFromCredit(
                expert.getCredit()
        );
    }

    @Override
    @Transactional
    public void addSubServiceForExpert(String username, Long subServiceId) {
        Expert expert = expertRepository.findByUser_Username(username)
                .orElseThrow(() -> new NotFoundException(Expert.class, username));
        if (expert.getStatus() != UserStatus.APPROVED)
            throw new NotApprovedUserException();
        SubService subService = subServiceService.findOptionalSubServiceById(subServiceId)
                .orElseThrow(() -> new NotFoundException(SubService.class, subServiceId));
//        if(expert.getSubServices().contains(subService))
//            throw new ExistenceException("subService for expert");
        expert.getSubServices().add(subService);
        expertRepository.save(expert);
    }

    @Override
    @Transactional
    public void removeSubServiceForExpert(String username, Long subServiceId) {
        Expert expert = expertRepository.findByUser_Username(username)
                .orElseThrow(() -> new NotFoundException(Expert.class, username));
        SubService subService = subServiceService.findOptionalSubServiceById(subServiceId)
                .orElseThrow(() -> new NotFoundException(SubService.class, subServiceId));
        expert.getSubServices().remove(subService);
        expertRepository.save(expert);
    }

    @Override
    @Transactional
    public List<SubServiceResponseDTO> findAllSubServicesOfExpert(Long expertId) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new NotFoundException(Expert.class, expertId));
        return expert.getSubServices().stream().map(
                SubServiceMapper::dtoFromSubService
        ).toList();
    }

    private void updateFields(Expert expert, ExpertUpdateDTO updateDTO) {
        if (updateDTO.getStatus() != null)
            expert.setStatus(updateDTO.getStatus());

        if (StringUtils.hasText(updateDTO.getPhoneNumber())) {
            if (expertRepository.existsByPhoneNumber(updateDTO.getPhoneNumber()))
                throw new ExistenceException("phoneNumber");
            expert.setPhoneNumber(updateDTO.getPhoneNumber());
        }

//        if (updateDTO.getProfileImage() != null)
//            expert.setProfileImage(updateDTO.getProfileImage());
    }
}
