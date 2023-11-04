package dev.rockyj.staffing_pro_api.services;

import dev.rockyj.staffing_pro_api.domain.dtos.CountryDTO;
import dev.rockyj.staffing_pro_api.domain.dtos.GeographyDTO;
import dev.rockyj.staffing_pro_api.repositories.CountriesRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CountryServices {

    private final CountriesRepository countriesRepository;

    public CountryServices(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    public List<CountryDTO> findAllCountries() {
        var countries = this.countriesRepository.findAll();
        return countries
                .stream()
                .map((var g) -> new CountryDTO(
                        g.getId().toString(),
                        g.getName(),
                        new GeographyDTO(g.getGeography().getId().toString(), g.getGeography().getName())
                ))
                .collect(Collectors.toList());
    }
}
