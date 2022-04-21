package com.wccgroup.assignment.mock;

import com.wccgroup.assignment.entity.Location;

import java.security.SecureRandom;
import java.util.Optional;

public class LocationMock {

    public static Optional<Location> getOne() {
        return Optional.of(Location
                .builder()
                .id(new SecureRandom().nextLong())
                .postcode("AB12 1AB")
                .latitude(99.9999)
                .longitude(-5.0000)
                .build());
    }

    public static Optional<Location> getOther() {
        return Optional.of(Location
                .builder()
                .id(new SecureRandom().nextLong())
                .postcode("AC12 1AC")
                .latitude(98.9999)
                .longitude(-4.0000)
                .build());
    }

}
