package com.knubisoft.bmwtesttask.service;

import com.knubisoft.bmwtesttask.db_model.Address;
import com.knubisoft.bmwtesttask.db_model.Geo;
import com.knubisoft.bmwtesttask.dto.AddressDTO;
import com.knubisoft.bmwtesttask.dto.GeoDTO;
import com.knubisoft.bmwtesttask.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final GeoService geoService;

    public void insertAddressToDatabase(final AddressDTO addressDTO, final GeoDTO geoDTO) {
        Geo geo = geoService.convertGeoDTOToGEO(geoDTO);
        Address address = convertAddressDTOToAddress(addressDTO, geo);
        addressRepository.save(address);
    }

    public Address convertAddressDTOToAddress(final AddressDTO addressDTO, final Geo geo) {
        return Address.builder()
                .city(addressDTO.getCity())
                .street(addressDTO.getStreet())
                .suite(addressDTO.getSuite())
                .zipcode(addressDTO.getZipCode())
                .geo(geo)
                .build();
    }
}
