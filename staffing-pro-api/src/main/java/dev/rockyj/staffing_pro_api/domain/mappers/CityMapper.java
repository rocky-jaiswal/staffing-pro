package dev.rockyj.staffing_pro_api.domain.mappers;

import dev.rockyj.staffing_pro_api.domain.dtos.CityDTO;
import dev.rockyj.staffing_pro_api.domain.entities.City;
import io.micronaut.context.annotation.Mapper;
import jakarta.inject.Singleton;

@Singleton
public interface CityMapper {

    @Mapper
    CityDTO toCityDTO(City city);
}
