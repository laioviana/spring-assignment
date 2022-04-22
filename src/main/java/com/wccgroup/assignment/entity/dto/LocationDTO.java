package com.wccgroup.assignment.entity.dto;

import lombok.NonNull;
/**
 * The DTO class for the entity {@link com.wccgroup.assignment.entity.Location }
 *
 */
public record LocationDTO(@NonNull double latitude, @NonNull double longitude) {

}
