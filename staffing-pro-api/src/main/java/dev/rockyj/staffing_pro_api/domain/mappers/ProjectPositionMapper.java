package dev.rockyj.staffing_pro_api.domain.mappers;

import dev.rockyj.staffing_pro_api.domain.dtos.ProjectPositionDTO;
import dev.rockyj.staffing_pro_api.domain.entities.ProjectPosition;
import io.micronaut.context.annotation.Mapper;
import jakarta.inject.Singleton;

@Singleton
public interface ProjectPositionMapper {

    @Mapper
    ProjectPositionDTO toProjectPositionDTO(ProjectPosition projectPosition);
}
