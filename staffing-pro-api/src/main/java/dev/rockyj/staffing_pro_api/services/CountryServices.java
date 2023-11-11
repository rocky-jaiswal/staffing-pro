package dev.rockyj.staffing_pro_api.services;

import dev.rockyj.staffing_pro_api.domain.dtos.CountryDTO;
import dev.rockyj.staffing_pro_api.domain.mappers.GeographyMapper;
import dev.rockyj.staffing_pro_api.repositories.CountriesRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
public class CountryServices {

    private final CountriesRepository countriesRepository;
    private final GeographyMapper geographyMapper;

    public List<CountryDTO> findAllCountries() {
        var countries = this.countriesRepository.findAll();
        return countries
                .stream()
                .map((var country) -> new CountryDTO(
                        country.getId().toString(),
                        country.getName(),
                        geographyMapper.toGeographyDTO(country.getGeography()))
                )
                .collect(Collectors.toList());
    }
}
