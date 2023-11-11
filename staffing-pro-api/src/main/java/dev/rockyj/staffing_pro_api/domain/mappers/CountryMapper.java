package dev.rockyj.staffing_pro_api.domain.mappers;

import dev.rockyj.staffing_pro_api.domain.dtos.CountryDTO;
import dev.rockyj.staffing_pro_api.domain.entities.Country;
import io.micronaut.context.annotation.Mapper;
import jakarta.inject.Singleton;

@Singleton
public interface CountryMapper {

    @Mapper
        //.Mapping(to = "geographyDTO", from = "geography")
    CountryDTO toCountryDTO(Country country);

}
