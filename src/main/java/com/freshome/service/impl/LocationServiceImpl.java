package com.freshome.service.impl;

import com.freshome.dto.location.LocationDTO;
import com.freshome.entity.Location;
import com.freshome.entity.Order;
import com.freshome.entity.entityMapper.LocationMapper;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.LocationRepository;
import com.freshome.service.LocationService;
import com.freshome.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final OrderService orderService;

    @Override
    @Transactional
    public LocationDTO saveLocation(
            Long orderId, LocationDTO locationDTO) {
        Order order = orderService.findOptionalOrderById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found!"));
        return LocationMapper.dtoFromLocation(
                locationRepository.save(
                        new Location(
                                locationDTO.latitude(),
                                locationDTO.longitude(), order))
        );
    }

    @Override
    public LocationDTO getLocationByOrder(Long orderId) {
        return LocationMapper.dtoFromLocation(
                locationRepository.findByOrder_Id(orderId)
                        .orElseThrow(() -> new NotFoundException("Location not found!"))
        );
    }

    @Override
    public LocationDTO getLocationById(Long id) {
        return LocationMapper.dtoFromLocation(
                locationRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Location not found!"))
        );
    }
}
