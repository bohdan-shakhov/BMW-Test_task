package com.knubisoft.bmwtesttask.converter;

import com.knubisoft.bmwtesttask.db_model.Geo;
import com.knubisoft.bmwtesttask.dto.GeoDTO;
import lombok.NoArgsConstructor;

public class GeoConverter {

    public static Geo convertGeoDTOToGEO(final GeoDTO geoDTO) {
        return Geo.builder()
                .lat(String.valueOf(geoDTO.getLat()))
                .lng(String.valueOf(geoDTO.getLng()))
                .build();
    }

}
