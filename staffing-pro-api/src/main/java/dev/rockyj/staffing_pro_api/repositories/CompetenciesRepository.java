package dev.rockyj.staffing_pro_api.repositories;

import dev.rockyj.staffing_pro_api.domain.entities.Competency;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;

import java.util.UUID;

@Repository
public interface CompetenciesRepository extends PageableRepository<Competency, UUID> {
}
