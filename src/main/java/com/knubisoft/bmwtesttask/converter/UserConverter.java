package com.knubisoft.bmwtesttask.converter;

import com.knubisoft.bmwtesttask.db_model.UserModel;
import com.knubisoft.bmwtesttask.dto.AddressDTO;
import com.knubisoft.bmwtesttask.dto.CompanyDTO;
import com.knubisoft.bmwtesttask.dto.UserDTO;

public class UserConverter {

    public static UserModel convertUserDTOToUser(UserDTO userDTO, AddressDTO addressDTO, CompanyDTO companyDTO) {
        return UserModel.builder()
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .phone(userDTO.getPhone())
                .username(userDTO.getUserName())
                .website(userDTO.getWebsite())
                .address(AddressConverter.convertAddressDTOToAddress(addressDTO, GeoConverter.convertGeoDTOToGEO(addressDTO.getGeo())))
                .company(CompanyConverter.convertCompanyDTOToCompany(companyDTO))
                .build();
    }

}
