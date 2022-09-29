package com.knubisoft.bmwtesttask.service;

import com.knubisoft.bmwtesttask.converter.GeoConverter;
import com.knubisoft.bmwtesttask.db_model.Geo;
import com.knubisoft.bmwtesttask.dto.GeoDTO;
import com.knubisoft.bmwtesttask.repository.GeoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeoService {

    private final GeoRepository geoRepository;

    public void insertGeoToDatabase(final GeoDTO geoDTO) {
        Geo geo = GeoConverter.convertGeoDTOToGEO(geoDTO);
        geoRepository.save(geo);
    }
}
