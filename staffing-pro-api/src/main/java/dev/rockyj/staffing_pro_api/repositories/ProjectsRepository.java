package dev.rockyj.staffing_pro_api.repositories;

import dev.rockyj.staffing_pro_api.entities.*;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaSpecificationExecutor;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;

import java.util.List;
import java.util.UUID;

@Log
@Repository
public abstract class ProjectsRepository implements PageableRepository<Project, UUID>, JpaSpecificationExecutor<Project> {

    EntityManager entityManager;

    public ProjectsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //TODO: Use page for filtered data
    @Transactional
    public Page<Project> findByCountry(Pageable pageable, String countryId) {
        // log.info("->" + pageable.toString());
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Project> criteriaQuery = builder.createQuery(Project.class);

        Root<Project> project = criteriaQuery.from(Project.class);
        ListJoin<Project, City> cityListJoin = project.join(Project_.projectCities);
        Join<City, Country> cityCountryListJoin = cityListJoin.join(City_.country);

        var predicate = builder.equal(cityCountryListJoin.get(Country_.ID), UUID.fromString(countryId));
        var query = entityManager
                .createQuery(criteriaQuery.where(predicate).orderBy(builder.desc(project.get("createdAt"))));
        var allResultsSize = query.getResultList().size();
        // log.info("size ->" + allResultsSize);
        var results = query
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getSize())
                .getResultList();

        return new Page<Project>() {
            @Override
            public long getTotalSize() {
                return allResultsSize;
            }

            @Override
            public int getTotalPages() {
                return allResultsSize / pageable.getSize();
            }

            @Override
            public @NonNull List<Project> getContent() {
                return results;
            }

            @Override
            public @NonNull Pageable getPageable() {
                return pageable;
            }
        };
    }

    // TODO: Try and solve n+1 problem
    @Transactional
    public List<Project> findAllProjectsWithDetails() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Project> criteriaQuery = builder.createQuery(Project.class);

        Root<Project> project = criteriaQuery.from(Project.class);
        Path<UUID> idPath = project.get(Project_.id);

        Join<Project, Company> projectCompanyJoin = project.join(Project_.COMPANY);
        Path<String> companyName = projectCompanyJoin.get(Company_.NAME);

        project.join(Project_.INDUSTRY);
        project.join(Project_.VERTICAL);

        Join<Project, City> cityJoin = project.join(Project_.PROJECT_CITIES);
        cityJoin.join(City_.COUNTRY);

        criteriaQuery.select(project);
        // criteriaQuery.select(builder.construct(ProjectDTO.class, idPath, companyName));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
