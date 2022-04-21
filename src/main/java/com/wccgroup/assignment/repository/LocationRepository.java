package com.wccgroup.assignment.repository;

import com.wccgroup.assignment.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findLocationByPostcode(String postCode);

}
