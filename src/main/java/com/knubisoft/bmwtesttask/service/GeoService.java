package com.knubisoft.bmwtesttask.service;

import com.knubisoft.bmwtesttask.db_model.Geo;
import com.knubisoft.bmwtesttask.dto.GeoDTO;
import com.knubisoft.bmwtesttask.repository.GeoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeoService {

    private final GeoRepository geoRepository;

    protected void insertGeoToDatabase(final GeoDTO geoDTO) {
        Geo geo = convertGeoDTOToGEO(geoDTO);
        geoRepository.save(geo);
    }

    protected Geo convertGeoDTOToGEO(final GeoDTO geoDTO) {
        return Geo.builder()
                .lat(String.valueOf(geoDTO.getLat()))
                .lng(String.valueOf(geoDTO.getLng()))
                .build();
    }
}
