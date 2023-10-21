package dev.rockyj.staffing_pro_api.controllers;

import dev.rockyj.staffing_pro_api.dtos.ProjectDTO;
import dev.rockyj.staffing_pro_api.services.ProjectService;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Map;

@ExecuteOn(TaskExecutors.IO)
@Controller("/v1/projects")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class ProjectsController {

    private final ProjectService projectService;

    private final Integer PAGE_SIZE = 10;

    ProjectsController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Get("/{pageNumber}")
    List<ProjectDTO> index(@PathVariable @NotBlank String pageNumber) {
        return this.projectService.findAllProjectsWithDetails(Pageable.from(Integer.parseInt(pageNumber), PAGE_SIZE));
    }

    @Get("/count")
    Map<String, Long> count() {
        return Map.of("count", this.projectService.countProjects());
    }
}
