package dev.rockyj.staffing_pro_api.repositories;

import dev.rockyj.staffing_pro_api.entities.Geography;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GeographiesRepository extends PageableRepository<Geography, UUID> {

    @Query("SELECT g FROM geographies g LEFT JOIN FETCH g.countries")
    List<Geography> findAllGeographiesAndCountries();
}
