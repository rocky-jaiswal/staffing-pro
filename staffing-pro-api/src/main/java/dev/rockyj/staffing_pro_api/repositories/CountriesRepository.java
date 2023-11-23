package dev.rockyj.staffing_pro_api.repositories;

import dev.rockyj.staffing_pro_api.domain.entities.Country;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CountriesRepository extends PageableRepository<Country, UUID> {

    List<Country> findByGeographyId(UUID geographyId);
}
