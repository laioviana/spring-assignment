package com.wccgroup.assignment.entity;

import lombok.Data;
import javax.persistence.*;
/**
 * The persistent class for the Location database table.
 *
 */
@Entity
@Data
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

}