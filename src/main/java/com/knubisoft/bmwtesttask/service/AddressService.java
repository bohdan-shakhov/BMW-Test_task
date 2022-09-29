package com.knubisoft.bmwtesttask.service;

import com.knubisoft.bmwtesttask.converter.AddressConverter;
import com.knubisoft.bmwtesttask.converter.GeoConverter;
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
        Geo geo = GeoConverter.convertGeoDTOToGEO(geoDTO);
        Address address = AddressConverter.convertAddressDTOToAddress(addressDTO, geo);
        addressRepository.save(address);
    }

}
