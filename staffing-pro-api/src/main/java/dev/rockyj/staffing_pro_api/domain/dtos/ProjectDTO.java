package dev.rockyj.staffing_pro_api.domain.dtos;

import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDate;
import java.util.List;

@Serdeable
public record ProjectDTO(String id,
                         String title,
                         String description,
                         String stage,
                         LocalDate startDate,
                         LocalDate endDate,
                         Boolean promoted,
                         Boolean hidden,
                         Boolean archived,
                         CompanyDTO company,
                         IndustryDTO industry,
                         VerticalDTO vertical,
                         List<CityDTO> cities,
                         List<ProjectPositionDTO> positions
) {
}
