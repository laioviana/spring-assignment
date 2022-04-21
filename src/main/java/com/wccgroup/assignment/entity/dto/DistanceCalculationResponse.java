package com.wccgroup.assignment.entity.dto;

import com.wccgroup.assignment.entity.Location;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DistanceCalculationResponse {

    Location locationA;

    Location locationB;

    Double distance;

    String unit;

}
