package dev.rockyj.staffing_pro_api.dtos;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record GeographyDTO(String id, String name) {
}
