package com.freshome.service;

import com.freshome.dto.location.LocationDTO;
import com.freshome.entity.Location;

public interface LocationService {
    LocationDTO saveLocation(
            Long orderId, LocationDTO locationDTO);

    LocationDTO getLocationByOrder(Long orderId);

    LocationDTO getLocationById(Long id);
}
