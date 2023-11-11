package dev.rockyj.staffing_pro_api.domain.mappers;

import dev.rockyj.staffing_pro_api.domain.dtos.IndustryDTO;
import dev.rockyj.staffing_pro_api.domain.entities.Industry;
import io.micronaut.context.annotation.Mapper;
import jakarta.inject.Singleton;

@Singleton
public interface IndustryMapper {

    @Mapper
    IndustryDTO toIndustryDTO(Industry industry);
}
