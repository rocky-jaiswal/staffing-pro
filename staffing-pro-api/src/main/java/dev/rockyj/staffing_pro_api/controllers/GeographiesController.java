package dev.rockyj.staffing_pro_api.controllers;

import dev.rockyj.staffing_pro_api.dtos.GeographyDTO;
import dev.rockyj.staffing_pro_api.services.GeographyServices;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/v1/geographies")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class GeographiesController {

    private final GeographyServices geographyServices;

    GeographiesController(GeographyServices geographyServices) {
        this.geographyServices = geographyServices;
    }

    @Get()
    List<GeographyDTO> index() {
        return this.geographyServices.findAllGeographies();
    }
}
