package dev.rockyj.staffing_pro_api.domain.mappers;

import dev.rockyj.staffing_pro_api.domain.dtos.GeographyDTO;
import dev.rockyj.staffing_pro_api.domain.entities.Geography;
import io.micronaut.context.annotation.Mapper;
import jakarta.inject.Singleton;

@Singleton
public interface GeographyMapper {

    @Mapper
    GeographyDTO toGeographyDTO(Geography geography);
}
