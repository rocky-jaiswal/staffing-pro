package dev.rockyj.staffing_pro_api.domain.mappers;

import dev.rockyj.staffing_pro_api.domain.dtos.CompanyDTO;
import dev.rockyj.staffing_pro_api.domain.entities.Company;
import io.micronaut.context.annotation.Mapper;
import jakarta.inject.Singleton;

@Singleton
public interface CompanyMapper {

    @Mapper
    CompanyDTO toCompanyDTO(Company company);
}
