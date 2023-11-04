package dev.rockyj.staffing_pro_api.controllers;

import dev.rockyj.staffing_pro_api.domain.dtos.CompetencyDTO;
import dev.rockyj.staffing_pro_api.services.CompetencyServices;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/v1/competencies")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class CompetenciesController {

    private final CompetencyServices competencyServices;

    public CompetenciesController(CompetencyServices competencyServices) {
        this.competencyServices = competencyServices;
    }

    @Get()
    List<CompetencyDTO> index() {
        return this.competencyServices.findAll();
    }
}
