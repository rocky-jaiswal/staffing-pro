package dev.rockyj.staffing_pro_api.services;

import dev.rockyj.staffing_pro_api.dtos.GeographyDTO;
import dev.rockyj.staffing_pro_api.repositories.GeographiesRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class GeographyServices {

    private final GeographiesRepository geographiesRepository;

    GeographyServices(GeographiesRepository geographiesRepository) {
        this.geographiesRepository = geographiesRepository;
    }

    public List<GeographyDTO> findAllGeographies() {
        var geographies = this.geographiesRepository.findAllGeographiesAndCountries();
        return geographies.stream().map((var g) -> new GeographyDTO(
                g.getId().toString(),
                g.getName()
        )).collect(Collectors.toList());
    }

}
