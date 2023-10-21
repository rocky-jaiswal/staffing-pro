package dev.rockyj.staffing_pro_api.entities;

import dev.rockyj.staffing_pro_api.dtos.IndustryDTO;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Serdeable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "industries")
public class Industry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String name;

    public IndustryDTO toDTO() {
        return new IndustryDTO(this.getId().toString(), this.getName());
    }

}
