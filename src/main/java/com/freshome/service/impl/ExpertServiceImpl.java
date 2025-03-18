package com.freshome.service.impl;

import com.freshome.entity.Credit;
import com.freshome.entity.Expert;
import com.freshome.entity.dto.credit.CreditCreateDTO;
import com.freshome.entity.dto.expert.ExpertCreatDTO;
import com.freshome.entity.dto.expert.ExpertResponseDTO;
import com.freshome.entity.dto.expert.ExpertUpdateDTO;
import com.freshome.entity.entityMapper.ExpertMapper;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.ExpertRepository;
import com.freshome.service.CreditService;
import com.freshome.service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
//@Transactional
public class ExpertServiceImpl implements ExpertService {

    private final ExpertRepository expertRepository;
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
    public ExpertResponseDTO updateExpert(ExpertUpdateDTO expertUpdateDto) {
        Expert expert = expertRepository.findById(expertUpdateDto.getId())
                .orElseThrow(() -> new NotFoundException(Expert.class, expertUpdateDto.getId()));
        return ExpertMapper.dtoFromExpert(
                expertRepository.save(
                        ExpertMapper.expertFromDto(expert, expertUpdateDto))
        );
    }
}
