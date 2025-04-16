package com.freshome.entity.entityMapper;

import com.freshome.dto.location.LocationDTO;
import com.freshome.entity.Location;

public class LocationMapper {

    public static LocationDTO dtoFromLocation (Location location) {
        return new LocationDTO(
                location.getLatitude(),
                location.getLongitude());
    }
}
