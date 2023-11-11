package dev.rockyj.staffing_pro_api.services;

import dev.rockyj.staffing_pro_api.domain.dtos.GeographyDTO;
import dev.rockyj.staffing_pro_api.domain.mappers.GeographyMapper;
import dev.rockyj.staffing_pro_api.repositories.GeographiesRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
public class GeographyServices {

    private final GeographiesRepository geographiesRepository;
    private final GeographyMapper geographyMapper;

    public List<GeographyDTO> findAllGeographies() {
        var geographies = this.geographiesRepository.findAllGeographiesAndCountries();
        return geographies.stream().map((var g) ->
                        new GeographyDTO(
                                g.getId().toString(),
                                g.getName()
                        ))
                .collect(Collectors.toList());
    }

}
