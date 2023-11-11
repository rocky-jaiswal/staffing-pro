package dev.rockyj.staffing_pro_api.domain.entities;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Serdeable
@Introspected
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String title;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Column(columnDefinition = "TEXT")
    @Enumerated(EnumType.STRING)
    private ProjectStage stage;

    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column
    private boolean promoted = false;

    @Column
    private boolean hidden = false;

    @Column
    private boolean archived = false;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "industry_id")
    private Industry industry;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vertical_id")
    private Vertical vertical;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_cities",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "city_id"))
    private List<City> projectCities = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProjectPosition> projectPositions = new ArrayList<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Project project = (Project) o;
        return getId() != null && Objects.equals(getId(), project.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public enum ProjectStage {
        NOT_STARTED,
        BEGINNING,
        IN_PROGRESS,
        WRAP_UP,
        POST_DELIVERY,
        MAINTENANCE
    }
}
