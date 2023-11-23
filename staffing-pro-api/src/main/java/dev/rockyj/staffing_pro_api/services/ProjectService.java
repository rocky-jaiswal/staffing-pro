package dev.rockyj.staffing_pro_api.services;

import dev.rockyj.staffing_pro_api.domain.dtos.CityDTO;
import dev.rockyj.staffing_pro_api.domain.dtos.ProjectDTO;
import dev.rockyj.staffing_pro_api.domain.dtos.ProjectPositionDTO;
import dev.rockyj.staffing_pro_api.domain.dtos.SkillDTO;
import dev.rockyj.staffing_pro_api.domain.entities.Project;
import dev.rockyj.staffing_pro_api.domain.mappers.CompanyMapper;
import dev.rockyj.staffing_pro_api.domain.mappers.IndustryMapper;
import dev.rockyj.staffing_pro_api.domain.mappers.VerticalMapper;
import dev.rockyj.staffing_pro_api.repositories.ProjectsRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.annotation.Nullable;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectsRepository projectsRepository;
    private final CompanyMapper companyMapper;
    private final IndustryMapper industryMapper;
    private final VerticalMapper verticalMapper;
    // private final CountryMapper countryMapper;

    public Map<String, Object> findAllProjectsWithDetails(Pageable pageable,
                                                          @Nullable String selectedGeographyId,
                                                          @Nullable String selectedCountryId,
                                                          @Nullable String selectedCompetencyId) {
        Page<Project> projects;

        if (selectedGeographyId == null && selectedCountryId == null && selectedCompetencyId == null) {
            projects = projectsRepository.findAll(pageable);
        } else {
            projects = projectsRepository.findByGeographyAndCountryAndCompetency(
                    pageable,
                    selectedGeographyId,
                    selectedCountryId,
                    selectedCompetencyId);
        }

        var projectList = projects.
                getContent()
                .stream()
                .map((var project) -> new ProjectDTO(
                        project.getId().toString(),
                        project.getTitle(),
                        project.getDescription(),
                        project.getStage().toString(),
                        project.getStartDate(),
                        project.getEndDate(),
                        project.isPromoted(),
                        project.isHidden(),
                        project.isArchived(),
                        companyMapper.toCompanyDTO(project.getCompany()),
                        industryMapper.toIndustryDTO(project.getIndustry()),
                        verticalMapper.toVerticalDTO(project.getVertical()),
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
                ))
                .collect(Collectors.toList());

        return Map.of("projects", projectList, "count", projects.getTotalSize());
    }
}
