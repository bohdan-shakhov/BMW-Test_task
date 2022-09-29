package com.knubisoft.bmwtesttask.converter;

import com.knubisoft.bmwtesttask.db_model.Address;
import com.knubisoft.bmwtesttask.db_model.Geo;
import com.knubisoft.bmwtesttask.dto.AddressDTO;
import lombok.NoArgsConstructor;

public class AddressConverter {

    public static Address convertAddressDTOToAddress(final AddressDTO addressDTO, final Geo geo) {
        return Address.builder()
                .city(addressDTO.getCity())
                .street(addressDTO.getStreet())
                .suite(addressDTO.getSuite())
                .zipcode(addressDTO.getZipCode())
                .geo(geo)
                .build();
    }

}
