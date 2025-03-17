package com.freshome.service.impl;

import com.freshome.entity.dto.ExpertCreatDTO;
import com.freshome.entity.dto.ExpertResponseDTO;
import com.freshome.entity.entityMapper.ExpertMapper;
import com.freshome.repository.ExpertRepository;
import com.freshome.service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpertServiceImpl implements ExpertService {

    private final ExpertRepository expertRepository;

    @Override
    public ExpertResponseDTO createExpert(ExpertCreatDTO expertCreatDTO) {
        return ExpertMapper.dtoFromExpert(
                expertRepository.save(
                        ExpertMapper.expertFromDto(expertCreatDTO))
        );
    }
}
