package dev.rockyj.staffing_pro_api.repositories;

import dev.rockyj.staffing_pro_api.domain.entities.*;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaSpecificationExecutor;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public abstract class ProjectsRepository implements PageableRepository<Project, UUID>, JpaSpecificationExecutor<Project> {

    private final EntityManager entityManager;

    //TODO: Use page for filtered data
    @Transactional
    public Page<Project> findByGeographyAndCountryAndCompetency(Pageable pageable,
                                                                String geographyId,
                                                                String countryId,
                                                                String competencyId) {
        // log.info("->" + pageable.toString());
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Project> criteriaQuery = builder.createQuery(Project.class);

        Root<Project> project = criteriaQuery.from(Project.class);
        Predicate predicate = null;

        if (geographyId != null) {
            ListJoin<Project, City> cityListJoin = project.join(Project_.projectCities);
            Join<City, Country> cityCountryListJoin = cityListJoin.join(City_.country);
            Join<Country, Geography> countryGeographyJoin = cityCountryListJoin.join(Country_.geography);
            predicate = builder.equal(countryGeographyJoin.get(Geography_.ID), UUID.fromString(geographyId));
        }

        if (countryId != null) {
            ListJoin<Project, City> cityListJoin = project.join(Project_.projectCities);
            Join<City, Country> cityCountryListJoin = cityListJoin.join(City_.country);
            predicate = builder.equal(cityCountryListJoin.get(Country_.ID), UUID.fromString(countryId));
        }

        if (competencyId != null) {
            ListJoin<Project, ProjectPosition> projectPositionListJoin = project.join(Project_.projectPositions);
            ListJoin<ProjectPosition, Skill> projectPositionSkillListJoin = projectPositionListJoin.join(ProjectPosition_.skills);
            Join<Skill, Competency> skillCompetencyJoin = projectPositionSkillListJoin.join(Skill_.competency);
            if (predicate == null) {
                predicate = builder.equal(skillCompetencyJoin.get(Competency_.ID), UUID.fromString(competencyId));
            } else {
                predicate = builder.and(predicate, builder.equal(skillCompetencyJoin.get(Competency_.ID), UUID.fromString(competencyId)));
            }
        }

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
