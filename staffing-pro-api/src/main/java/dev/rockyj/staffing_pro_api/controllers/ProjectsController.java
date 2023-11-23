package dev.rockyj.staffing_pro_api.controllers;

import dev.rockyj.staffing_pro_api.services.ProjectService;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@ExecuteOn(TaskExecutors.IO)
@Controller("/v1/projects")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class ProjectsController {

    private final ProjectService projectService;

    private final Integer PAGE_SIZE = 10;

    @Get("/{pageNumber}")
    Map<String, Object> index(@PathVariable @NotBlank String pageNumber,
                              @QueryValue @NotNull String geography,
                              @QueryValue @NotNull String country,
                              @QueryValue @NotNull String competency) {
        return this.projectService
                .findAllProjectsWithDetails(
                        Pageable.from(Integer.parseInt(pageNumber), PAGE_SIZE),
                        geography.isEmpty() ? null : geography,
                        country.isEmpty() ? null : country,
                        competency.isEmpty() ? null : competency);
    }
}
