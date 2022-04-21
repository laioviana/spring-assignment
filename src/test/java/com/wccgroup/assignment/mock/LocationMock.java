package com.wccgroup.assignment.mock;

import com.wccgroup.assignment.entity.Location;

import java.security.SecureRandom;
import java.util.Optional;

public class LocationMock {

    public static Optional<Location> getOne() {
        Location location = new Location();
        location.setId(new SecureRandom().nextLong());
        location.setPostcode("AB12 1AB");
        location.setLatitude(99.9999);
        location.setLongitude(-5.0000);
        return Optional.of(location);
    }

    public static Optional<Location> getOther() {
        Location location = new Location();
        location.setId(new SecureRandom().nextLong());
        location.setPostcode("AC12 1AC");
        location.setLatitude(98.9999);
        location.setLongitude(-4.0000);
        return Optional.of(location);
    }
}
