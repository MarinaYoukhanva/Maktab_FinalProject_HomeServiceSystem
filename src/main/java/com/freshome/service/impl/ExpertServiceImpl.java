package com.freshome.service.impl;

import com.freshome.entity.Credit;
import com.freshome.entity.Expert;
import com.freshome.dto.credit.CreditCreateDTO;
import com.freshome.dto.expert.ExpertCreatDTO;
import com.freshome.dto.expert.ExpertResponseDTO;
import com.freshome.dto.expert.ExpertUpdateDTO;
import com.freshome.entity.entityMapper.ExpertMapper;
import com.freshome.exception.ChangePasswordException;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.ExpertRepository;
import com.freshome.service.CreditService;
import com.freshome.service.ExpertService;
import com.freshome.specification.ExpertSpecification;
import com.freshome.specification.Operator;
import jakarta.persistence.metamodel.SingularAttribute;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
//@Transactional
public class ExpertServiceImpl implements ExpertService {

    private final ExpertRepository expertRepository;
    private final PasswordEncoder passwordEncoder;
    private final CreditService creditService;


    @Override
    @Transactional
    public ExpertResponseDTO createExpert(ExpertCreatDTO expertCreatDTO) {

        Expert expert = ExpertMapper.expertFromDto(expertCreatDTO);
        Credit credit = creditService.createReturnCredit(new CreditCreateDTO(0L));
        expert.setCredit(credit);

        return ExpertMapper.dtoFromExpert(
                expertRepository.save(expert)
        );
    }

    @Override
    public ExpertResponseDTO findExpertById(Long id) {
        return ExpertMapper.dtoFromExpert(
                expertRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(Expert.class, id))
        );
    }

    @Override
    public Optional<Expert> findOptionalExpertById(Long id) {
        return expertRepository.findById(id);
    }

    @Override
    public List<ExpertResponseDTO> findAllExperts() {
        return expertRepository.findAll()
                .stream().map(ExpertMapper::dtoFromExpert)
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
    ){
        return expertRepository.findAll(
                ExpertSpecification.searchExpert(fields, operators, values, expertise))
                .stream().map(ExpertMapper::dtoFromExpert)
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

    private void updateFields (Expert expert, ExpertUpdateDTO updateDTO) {
        if (StringUtils.hasText(updateDTO.getFirstname()))
            expert.setFirstname(updateDTO.getFirstname());

        if (StringUtils.hasText(updateDTO.getLastname()))
            expert.setLastname(updateDTO.getLastname());

        if (StringUtils.hasText(updateDTO.getEmail()))
            expert.setEmail(updateDTO.getEmail());

        if (updateDTO.getStatus() != null)
            expert.setStatus(updateDTO.getStatus());

        if (StringUtils.hasText(updateDTO.getPhoneNumber()))
            expert.setPhoneNumber(updateDTO.getPhoneNumber());

        if (updateDTO.getProfileImage() != null)
            expert.setProfileImage(updateDTO.getProfileImage());
    }
}
