package dev.rockyj.staffing_pro_api.domain.dtos;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CompanyDTO(String id, String name) {
}