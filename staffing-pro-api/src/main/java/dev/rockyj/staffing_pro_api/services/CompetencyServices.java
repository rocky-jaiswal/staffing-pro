package dev.rockyj.staffing_pro_api.services;

import dev.rockyj.staffing_pro_api.domain.dtos.CompetencyDTO;
import dev.rockyj.staffing_pro_api.domain.entities.Competency;
import dev.rockyj.staffing_pro_api.domain.mappers.CompetencyMapper;
import dev.rockyj.staffing_pro_api.repositories.CompetenciesRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
public class CompetencyServices {

    private final CompetenciesRepository competenciesRepository;
    private final CompetencyMapper competencyMapper;

    public List<CompetencyDTO> findAll() {
        List<Competency> competencies = competenciesRepository.findAll();

        return competencies
                .stream()
                .map(competencyMapper::toCompetencyDTO)
                .collect(Collectors.toList());
    }
}
