package dev.rockyj.staffing_pro_api.entities;

import dev.rockyj.staffing_pro_api.dtos.VerticalDTO;
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
@Entity(name = "verticals")
public class Vertical {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String name;

    public VerticalDTO toDTO() {
        return new VerticalDTO(this.getId().toString(), this.getName());
    }

}
