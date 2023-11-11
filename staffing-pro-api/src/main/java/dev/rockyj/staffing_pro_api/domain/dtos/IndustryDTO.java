package dev.rockyj.staffing_pro_api.domain.dtos;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Introspected
public record IndustryDTO(String id, String name) {
}
