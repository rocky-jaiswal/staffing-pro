package dev.rockyj.staffing_pro_api.domain.mappers;

import dev.rockyj.staffing_pro_api.domain.dtos.ProjectDTO;
import dev.rockyj.staffing_pro_api.domain.entities.Project;
import io.micronaut.context.annotation.Mapper;
import jakarta.inject.Singleton;

@Singleton
public interface ProjectMapper {

    @Mapper
    ProjectDTO toProjectDTO(Project project);
}
