package dev.rockyj.staffing_pro_api.domain.mappers;

import dev.rockyj.staffing_pro_api.domain.dtos.CompetencyDTO;
import dev.rockyj.staffing_pro_api.domain.entities.Competency;
import io.micronaut.context.annotation.Mapper;
import jakarta.inject.Singleton;

@Singleton
public interface CompetencyMapper {
    @Mapper
    CompetencyDTO toCompetencyDTO(Competency competency);
}
