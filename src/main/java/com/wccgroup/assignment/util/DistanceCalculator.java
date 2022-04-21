package com.wccgroup.assignment.util;

import java.util.Optional;

public class DistanceCalculator {

    private final static double EARTH_RADIUS = 6371; // radius in kilometers

    public static Optional<Double> calculateDistance(double latitudeA, double longitudeA, double latitudeB, double longitudeB) {

        double lon1Radians = Math.toRadians(longitudeA);
        double lon2Radians = Math.toRadians(longitudeB);
        double lat1Radians = Math.toRadians(latitudeA);
        double lat2Radians = Math.toRadians(latitudeB);

        double a = haversine(lat1Radians, lat2Radians)
                + Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return Optional.of((EARTH_RADIUS * c));
    }

    private static double haversine(double deg1, double deg2) {
        return square(Math.sin((deg1 - deg2) / 2.0));
    }

    private static double square(double x) {
        return x * x;
    }
}
