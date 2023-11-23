package dev.rockyj.staffing_pro_api.services;

import dev.rockyj.staffing_pro_api.domain.dtos.CountryDTO;
import dev.rockyj.staffing_pro_api.domain.mappers.GeographyMapper;
import dev.rockyj.staffing_pro_api.repositories.CountriesRepository;
import jakarta.annotation.Nullable;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
public class CountryServices {

    private final CountriesRepository countriesRepository;
    private final GeographyMapper geographyMapper;

    public List<CountryDTO> findAllCountries(@Nullable String geographyId) {
        var countries = geographyId == null ?
                this.countriesRepository.findAll() :
                this.countriesRepository.findByGeographyId(UUID.fromString(geographyId));

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
