package dev.rockyj.staffing_pro_api.dtos;

import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDate;
import java.util.List;

@Serdeable
public record ProjectPositionDTO(String id,
                                 String title,
                                 String description,
                                 LocalDate startDate,
                                 LocalDate endDate,
                                 Boolean isOpen,
                                 List<SkillDTO> skills) {
}
