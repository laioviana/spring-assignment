package com.wccgroup.assignment.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "location")
public class Location {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "postcode", nullable = false)
    private String postcode;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    public Location() {

    }
}