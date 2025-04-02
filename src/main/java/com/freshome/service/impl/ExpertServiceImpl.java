package com.freshome.service.impl;

import com.freshome.entity.Credit;
import com.freshome.entity.Expert;
import com.freshome.dto.credit.CreditCreateDTO;
import com.freshome.dto.expert.ExpertCreatDTO;
import com.freshome.dto.expert.ExpertResponseDTO;
import com.freshome.dto.expert.ExpertUpdateDTO;
import com.freshome.entity.entityMapper.ExpertMapper;
import com.freshome.exception.ChangePasswordException;
import com.freshome.exception.ExistenceException;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.ExpertRepository;
import com.freshome.repository.ReviewRepository;
import com.freshome.service.CreditService;
import com.freshome.service.ExpertService;
import com.freshome.service.ReviewService;
import com.freshome.specification.ExpertSpecification;
import com.freshome.specification.Operator;
import jakarta.persistence.metamodel.SingularAttribute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
    //    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

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
        expert.setScore(0.0);

        return ExpertMapper.dtoFromExpert(
                expertRepository.save(expert)
        );
    }

    @Override
    public ExpertResponseDTO findExpertById(Long id) {
        Expert expert = expertRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Expert.class, id));
        Double score = reviewRepository.expertScoreFromRatingsAverage(id);
//        expert.setScore(score == null ? 0.0 : score);
        expert.setScore(score);
        return ExpertMapper.dtoFromExpert(expert);
    }

    @Override
    public Optional<Expert> findOptionalExpertById(Long id) {
        return expertRepository.findById(id)
                .map(expert -> {
                    Double score = reviewRepository.expertScoreFromRatingsAverage(id);
//                    expert.setScore(score == null ? 0.0 : score);
                    expert.setScore(score);
                    return expert;
                });
    }

    @Override
    public List<ExpertResponseDTO> findAllExperts() {
        List<Expert> experts = expertRepository.findAll();
        experts.forEach(expert -> {
            Double score = reviewRepository.expertScoreFromRatingsAverage(expert.getId());
            expert.setScore(score);
        });
        return experts.stream()
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
            List<SingularAttribute<?, ?>> fields, List<Operator> operators, List<String> values,
            String expertise
    ) {
        List<Expert> experts = expertRepository.findAll(
                ExpertSpecification.searchExpert(fields, operators, values, expertise));
        experts.forEach(expert -> {
            Double score = reviewRepository.expertScoreFromRatingsAverage(expert.getId());
            expert.setScore(score);
        });
        return experts.stream()
                .map(ExpertMapper::dtoFromExpert)
                .toList();
    }

    @Override
    @Transactional
    public void changePassword(Long expertId, String oldPassword, String newPassword) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new NotFoundException(Expert.class, expertId));
        if (oldPassword.equals(newPassword)
                || !passwordEncoder.matches(oldPassword, expert.getPassword()))
            throw new ChangePasswordException();
        expert.setPassword(passwordEncoder.encode(newPassword));
        expertRepository.save(expert);
    }

    private void updateFields(Expert expert, ExpertUpdateDTO updateDTO) {
        if (StringUtils.hasText(updateDTO.getFirstname()))
            expert.setFirstname(updateDTO.getFirstname());

        if (StringUtils.hasText(updateDTO.getLastname()))
            expert.setLastname(updateDTO.getLastname());

        if (StringUtils.hasText(updateDTO.getEmail())){
            if (expertRepository.existsByEmail(updateDTO.getEmail()))
                throw new ExistenceException("email");
            expert.setEmail(updateDTO.getEmail());
        }

        if (updateDTO.getStatus() != null)
            expert.setStatus(updateDTO.getStatus());

        if (StringUtils.hasText(updateDTO.getPhoneNumber()))
            expert.setPhoneNumber(updateDTO.getPhoneNumber());

        if (updateDTO.getProfileImage() != null)
            expert.setProfileImage(updateDTO.getProfileImage());
    }
}
