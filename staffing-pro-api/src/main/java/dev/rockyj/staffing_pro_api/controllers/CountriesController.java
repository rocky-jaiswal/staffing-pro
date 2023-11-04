package dev.rockyj.staffing_pro_api.controllers;

import dev.rockyj.staffing_pro_api.domain.dtos.CountryDTO;
import dev.rockyj.staffing_pro_api.services.CountryServices;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.Comparator;
import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/v1/countries")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class CountriesController {

    private final CountryServices countryServices;

    CountriesController(CountryServices countryServices) {
        this.countryServices = countryServices;
    }

    @Get()
    List<CountryDTO> index() {
        var countries = this.countryServices.findAllCountries();
        countries.sort(Comparator.comparing(CountryDTO::name));
        return countries;
    }
}
