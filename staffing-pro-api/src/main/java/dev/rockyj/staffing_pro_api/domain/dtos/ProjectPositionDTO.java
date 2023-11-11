package dev.rockyj.staffing_pro_api.domain.dtos;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDate;
import java.util.List;

@Serdeable
@Introspected
public record ProjectPositionDTO(String id,
                                 String title,
                                 String description,
                                 LocalDate startDate,
                                 LocalDate endDate,
                                 Boolean isOpen,
                                 List<SkillDTO> skills) {
}
