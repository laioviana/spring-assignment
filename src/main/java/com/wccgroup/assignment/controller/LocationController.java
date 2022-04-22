package com.wccgroup.assignment.controller;

import com.wccgroup.assignment.entity.Location;
import com.wccgroup.assignment.entity.dto.DistanceCalculationResponse;
import com.wccgroup.assignment.entity.dto.LocationDTO;
import com.wccgroup.assignment.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Log4j2
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/location", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationController {

    private final LocationService locationService;

    /**
     * Calculates the distance between two postcodes
     *
     * @param postcodeA first postcode necessary to calculate the distance
     * @param postcodeB second postcode necessary to calculate the distance
     * @return ResponseEntity - ResponseEntity with body having object of type {@link com.wccgroup.assignment.entity.dto.DistanceCalculationResponse}
     */
    @GetMapping("{postcodeA}/{postcodeB}")
    ResponseEntity<DistanceCalculationResponse> getDistanceByPostCode(@PathVariable(name = "postcodeA") @Valid @Size(min = 8, max = 8, message = "Wrong format of post code. Ex: 99XX 9XX") String postcodeA,
                                                                      @PathVariable(name = "postcodeB") @Valid @Size(min = 8, max = 8, message = "Wrong format of post code. Ex: 99XX 9XX") String postcodeB) {
        log.info("Calculating distance between {} and {}.", postcodeA, postcodeB);
        return locationService.getDistanceBetweenLocations(postcodeA.toUpperCase(), postcodeB.toUpperCase())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Updates Location with new latitude and longitude
     *
     * @param locationId id for the location that will be updated
     * @param locationDTO a DTO object with latitude and longitude that will be updated
     * @return ResponseEntity - ResponseEntity with body having the updated object of type {@link com.wccgroup.assignment.entity.Location}
     */
    @PutMapping("{locationId}")
    ResponseEntity<Location> updateLatLongByLocationId(@PathVariable Long locationId, @RequestBody @Valid LocationDTO locationDTO){
        log.info("Updating location id {} with lat {} and long {}.", locationId, locationDTO.latitude(), locationDTO.longitude());
        return locationService.updateLocation(locationId, locationDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }




}
