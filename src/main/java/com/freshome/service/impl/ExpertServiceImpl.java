package com.freshome.service.impl;

import com.freshome.dto.ChangePasswordDTO;
import com.freshome.dto.credit.CreditResponseDTO;
import com.freshome.dto.subService.SubServiceResponseDTO;
import com.freshome.entity.Credit;
import com.freshome.entity.Expert;
import com.freshome.dto.credit.CreditCreateDTO;
import com.freshome.dto.expert.ExpertCreatDTO;
import com.freshome.dto.expert.ExpertResponseDTO;
import com.freshome.dto.expert.ExpertUpdateDTO;
import com.freshome.entity.SubService;
import com.freshome.entity.entityMapper.CreditMapper;
import com.freshome.entity.entityMapper.ExpertMapper;
import com.freshome.entity.entityMapper.SubServiceMapper;
import com.freshome.exception.ChangePasswordException;
import com.freshome.exception.ExistenceException;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.ExpertRepository;
import com.freshome.service.CreditService;
import com.freshome.service.ExpertService;
import com.freshome.service.SubServiceService;
import com.freshome.specification.ExpertSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
//@Transactional
public class ExpertServiceImpl implements ExpertService {

    private final ExpertRepository expertRepository;
    private final PasswordEncoder passwordEncoder;
    private final CreditService creditService;
    private final SubServiceService subServiceService;

    @Override
    @Transactional
    public ExpertResponseDTO createExpert(ExpertCreatDTO expertCreatDTO) throws IOException {
        log.info("create expert in service triggered");
        if (expertRepository.existsByEmail(expertCreatDTO.getEmail()))
            throw new ExistenceException("email");
        if (expertRepository.existsByPhoneNumber(expertCreatDTO.getPhoneNumber()))
            throw new ExistenceException("phoneNumber");

        Expert expert = ExpertMapper.expertFromDto(expertCreatDTO);
//        expert.setProfileImage(photo.getBytes());
        Credit credit = creditService.createReturnCredit(new CreditCreateDTO(0L));
        expert.setCredit(credit);
//        expert.setScore(0.0);

        return ExpertMapper.dtoFromExpert(
                expertRepository.save(expert)
        );
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
    @Transactional
    public void deleteExpertById(Long id) {
        if (!expertRepository.existsById(id))
            throw new NotFoundException(Expert.class, id);
        expertRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ExpertResponseDTO updateExpert(ExpertUpdateDTO updateDTO) {
        Expert expert = expertRepository.findById(updateDTO.getId())
                .orElseThrow(() -> new NotFoundException(Expert.class, updateDTO.getId()));
        updateFields(expert, updateDTO);
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
    public void changePassword(Long expertId, ChangePasswordDTO dto) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new NotFoundException(Expert.class, expertId));
        if (dto.oldPassword().equals(dto.newPassword())
                || !passwordEncoder.matches(dto.oldPassword(), expert.getPassword()))
            throw new ChangePasswordException();
        expert.setPassword(passwordEncoder.encode(dto.newPassword()));
        expertRepository.save(expert);
    }

    @Override
    public CreditResponseDTO findCreditForExpert(Long expertId) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new NotFoundException(Expert.class, expertId));
        return CreditMapper.dtoFromCredit(
                expert.getCredit()
        );
    }

    @Override
    @Transactional
    public void addSubServiceForExpert(Long expertId, Long subServiceId) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new NotFoundException(Expert.class, expertId));
        SubService subService = subServiceService.findOptionalSubServiceById(subServiceId)
                .orElseThrow(() -> new NotFoundException(SubService.class, subServiceId));
//        if(expert.getSubServices().contains(subService))
//            throw new ExistenceException("subService for expert");
        expert.getSubServices().add(subService);
        expertRepository.save(expert);
    }

    @Override
    @Transactional
    public void removeSubServiceForExpert(Long expertId, Long subServiceId) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new NotFoundException(Expert.class, expertId));
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
        if (StringUtils.hasText(updateDTO.getFirstname()))
            expert.setFirstname(updateDTO.getFirstname());

        if (StringUtils.hasText(updateDTO.getLastname()))
            expert.setLastname(updateDTO.getLastname());

        if (StringUtils.hasText(updateDTO.getEmail())) {
            if (expertRepository.existsByEmail(updateDTO.getEmail()))
                throw new ExistenceException("email");
            expert.setEmail(updateDTO.getEmail());
        }

        if (updateDTO.getStatus() != null)
            expert.setStatus(updateDTO.getStatus());

        if (StringUtils.hasText(updateDTO.getPhoneNumber()))
            expert.setPhoneNumber(updateDTO.getPhoneNumber());

//        if (updateDTO.getProfileImage() != null)
//            expert.setProfileImage(updateDTO.getProfileImage());
    }
}
