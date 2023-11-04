package dev.rockyj.staffing_pro_api.domain.dtos;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CityDTO(String id, String name, CountryDTO country) {
}
