package com.wccgroup.assignment.controller;

import com.wccgroup.assignment.entity.Location;
import com.wccgroup.assignment.entity.dto.DistanceCalculationResponse;
import com.wccgroup.assignment.entity.dto.LocationDTO;
import com.wccgroup.assignment.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Inject)
@Validated
@RequestMapping(path = "/location", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationController {

    private final LocationService locationService;

    @GetMapping("{postcodeA}/{postcodeB}")
    ResponseEntity<DistanceCalculationResponse> getDistanceByPostCode(@PathVariable(name = "postcodeA") @Valid @Size(min = 8, max = 8, message = "Wrong format of post code. Ex: 99XX 9XX") String postcodeA,
                                                                      @PathVariable(name = "postcodeB") @Valid @Size(min = 8, max = 8, message = "Wrong format of post code. Ex: 99XX 9XX") String postcodeB) {
        return locationService.getDistanceBetweenLocations(postcodeA.toUpperCase(), postcodeB.toUpperCase())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{locationId}")
    ResponseEntity<Location> updateLatLongByLocationId(@PathVariable Long locationId, @RequestBody @Valid LocationDTO locationDTO){
        return locationService.updateLocation(locationId, locationDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }




}
