package dev.rockyj.staffing_pro_api.controllers;

import dev.rockyj.staffing_pro_api.domain.dtos.GeographyDTO;
import dev.rockyj.staffing_pro_api.services.GeographyServices;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ExecuteOn(TaskExecutors.IO)
@Controller("/v1/geographies")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class GeographiesController {

    private final GeographyServices geographyServices;
    
    @Get()
    List<GeographyDTO> index() {
        return this.geographyServices.findAllGeographies();
    }
}
