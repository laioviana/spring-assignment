package com.wccgroup.assignment.service;

import com.wccgroup.assignment.entity.Location;
import com.wccgroup.assignment.entity.dto.DistanceCalculationResponse;
import com.wccgroup.assignment.entity.dto.LocationDTO;
import com.wccgroup.assignment.repository.LocationRepository;
import com.wccgroup.assignment.util.DistanceCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
public class LocationService {

    private final LocationRepository locationRepository;

    private static final String UNIT = "km";

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Optional<Location> updateLocation(Long locationId, LocationDTO locationDTO){
        return locationRepository.findById(locationId).map(oldLocation -> {
            oldLocation.setLatitude(locationDTO.latitude());
            oldLocation.setLongitude(locationDTO.longitude());
            return locationRepository.save(oldLocation);
        });
    }

    public Optional<DistanceCalculationResponse> getDistanceBetweenLocations(String postcodeA, String postcodeB) {

        return getLocationsByPostCode(postcodeA, postcodeB)
                .flatMap(locationPair ->
                        DistanceCalculator.calculateDistance(
                            locationPair.getFirst().getLatitude(), locationPair.getFirst().getLongitude(),
                            locationPair.getSecond().getLatitude(), locationPair.getSecond().getLongitude())
                        .map(distance -> DistanceCalculationResponse.builder()
                                        .distance(distance)
                                        .locationA(locationPair.getFirst())
                                        .locationB(locationPair.getSecond())
                                        .unit(UNIT)
                                        .build()));
    }

    private Optional<Pair<Location, Location>> getLocationsByPostCode(String postcodeA, String postcodeB) {
        return locationRepository.findLocationByPostcode(postcodeA)
                .flatMap(location -> locationRepository.findLocationByPostcode(postcodeB)
                        .map(location2 -> Pair.of(location, location2)));
    }

}
