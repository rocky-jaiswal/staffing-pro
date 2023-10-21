package dev.rockyj.staffing_pro_api.services;

import dev.rockyj.staffing_pro_api.dtos.CityDTO;
import dev.rockyj.staffing_pro_api.dtos.ProjectDTO;
import dev.rockyj.staffing_pro_api.dtos.ProjectPositionDTO;
import dev.rockyj.staffing_pro_api.dtos.SkillDTO;
import dev.rockyj.staffing_pro_api.repositories.ProjectsRepository;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ProjectService {

    private final ProjectsRepository projectsRepository;

    ProjectService(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    public Long countProjects() {
        return projectsRepository.count();
    }

    public List<ProjectDTO> findAllProjectsWithDetails(Pageable pageable) {
        var projects = projectsRepository.findAll(pageable);

        return projects.
                getContent()
                .stream().map((var project) -> new ProjectDTO(
                        project.getId().toString(),
                        project.getTitle(),
                        project.getDescription(),
                        project.getStage().toString(),
                        project.getStartDate(),
                        project.getEndDate(),
                        project.isPromoted(),
                        project.isHidden(),
                        project.isArchived(),
                        project.getCompany().toDTO(),
                        project.getIndustry().toDTO(),
                        project.getVertical().toDTO(),
                        project.getProjectCities()
                                .stream()
                                .map((var city) -> new CityDTO(
                                        city.getId().toString(),
                                        city.getName(),
                                        city.getCountry().toDTO())).toList(),
                        project.getProjectPositions()
                                .stream()
                                .map((var position) -> new ProjectPositionDTO(
                                        position.getId().toString(),
                                        position.getTitle(),
                                        position.getDescription(),
                                        position.getStartDate(),
                                        position.getEndDate(),
                                        position.isOpen(),
                                        position.getSkills()
                                                .stream()
                                                .map((var skill) ->
                                                        new SkillDTO(skill.getId().toString(),
                                                                skill.getName(),
                                                                skill.getDescription()))
                                                .collect(Collectors.toList())
                                )).collect(Collectors.toList())
                )).collect(Collectors.toList());
    }
}
