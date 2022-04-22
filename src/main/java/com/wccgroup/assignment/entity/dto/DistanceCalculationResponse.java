package com.wccgroup.assignment.entity.dto;

import com.wccgroup.assignment.entity.Location;
import lombok.Builder;
import lombok.Data;
/**
 * The DTO class for the calculation response
 *
 */
@Data
@Builder
public class DistanceCalculationResponse {

    Location locationA;

    Location locationB;

    Double distance;

    String unit;

}
