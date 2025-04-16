package com.freshome.web;

import com.freshome.dto.location.LocationDTO;
import com.freshome.entity.Location;
import com.freshome.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping("/save")
    public ResponseEntity<LocationDTO> saveLocation (
            @RequestParam Long orderId,
            @RequestBody LocationDTO locationDTO
    ){
        return ResponseEntity.ok(
                locationService.saveLocation(orderId, locationDTO)
        );
    }

    @GetMapping("/find/{orderId}")
    public ResponseEntity<LocationDTO> findLocation (
            @PathVariable(value = "orderId") Long orderId
    ){
        return ResponseEntity.ok(
                locationService.getLocationByOrder(orderId)
        );
    }

    @GetMapping("/find/by_id/{id}")
    public ResponseEntity<LocationDTO> findLocationById (
            @PathVariable(value = "id") Long id
    ){
        return ResponseEntity.ok(
                locationService.getLocationById(id)
        );
    }
}
