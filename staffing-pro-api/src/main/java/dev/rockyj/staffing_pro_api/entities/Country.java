package dev.rockyj.staffing_pro_api.entities;

import dev.rockyj.staffing_pro_api.dtos.CountryDTO;
import dev.rockyj.staffing_pro_api.dtos.GeographyDTO;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Serdeable
@Data
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
    private List<City> cities = new ArrayList<>();

    public CountryDTO toDTO() {
        return new CountryDTO(this.getId().toString(), this.getName(), new GeographyDTO(this.getGeography().getId().toString(), this.getGeography().getName()));
    }

}
