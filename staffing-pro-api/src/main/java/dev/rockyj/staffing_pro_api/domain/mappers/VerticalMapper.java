package dev.rockyj.staffing_pro_api.domain.mappers;

import dev.rockyj.staffing_pro_api.domain.dtos.VerticalDTO;
import dev.rockyj.staffing_pro_api.domain.entities.Vertical;
import io.micronaut.context.annotation.Mapper;
import jakarta.inject.Singleton;

@Singleton
public interface VerticalMapper {

    @Mapper
    VerticalDTO toVerticalDTO(Vertical vertical);
}
