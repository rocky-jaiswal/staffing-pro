package dev.rockyj.staffing_pro_api.domain.dtos;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CountryDTO(String id, String name, GeographyDTO geography) {
}
