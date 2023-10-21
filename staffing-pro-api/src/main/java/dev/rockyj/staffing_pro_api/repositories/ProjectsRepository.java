package dev.rockyj.staffing_pro_api.repositories;

import dev.rockyj.staffing_pro_api.entities.*;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaSpecificationExecutor;
import io.micronaut.data.repository.PageableRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public abstract class ProjectsRepository implements PageableRepository<Project, UUID>, JpaSpecificationExecutor<Project> {

    EntityManager entityManager;

    public ProjectsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
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
