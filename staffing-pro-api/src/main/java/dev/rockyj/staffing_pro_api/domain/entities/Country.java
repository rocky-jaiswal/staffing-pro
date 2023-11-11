package dev.rockyj.staffing_pro_api.domain.entities;

import dev.rockyj.staffing_pro_api.domain.dtos.CountryDTO;
import dev.rockyj.staffing_pro_api.domain.dtos.GeographyDTO;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

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
@Entity(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String name;

    @ManyToOne
    @JoinColumn(name = "geography_id")
    private Geography geography;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<City> cities = new ArrayList<>();

    public CountryDTO toDTO() {
        return new CountryDTO(this.getId().toString(), this.getName(), new GeographyDTO(this.getGeography().getId().toString(), this.getGeography().getName()));
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Country country = (Country) o;
        return getId() != null && Objects.equals(getId(), country.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
