package dev.rockyj.staffing_pro_api.controllers;

import dev.rockyj.staffing_pro_api.domain.dtos.CountryDTO;
import dev.rockyj.staffing_pro_api.services.CountryServices;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@ExecuteOn(TaskExecutors.IO)
@Controller("/v1/countries")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class CountriesController {

    private final CountryServices countryServices;

    @Get()
    List<CountryDTO> index(@QueryValue @Nullable String geography) {
        var countries = this.countryServices.findAllCountries(geography);
        countries.sort(Comparator.comparing(CountryDTO::name));
        return countries;
    }
}
