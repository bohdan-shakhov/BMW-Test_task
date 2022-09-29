package com.knubisoft.bmwtesttask.service;

import com.knubisoft.bmwtesttask.db_model.UserModel;
import com.knubisoft.bmwtesttask.dto.AddressDTO;
import com.knubisoft.bmwtesttask.dto.CompanyDTO;
import com.knubisoft.bmwtesttask.dto.UserDTO;
import com.knubisoft.bmwtesttask.repository.UserModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String URI = "https://jsonplaceholder.typicode.com/users";
    private final UserModelRepository userRepository;
    private final GeoService geoService;
    private final CompanyService companyService;
    private final AddressService addressService;

    private <T> ResponseEntity<T> callToRemoteEndpoint(final String uri, final Class<T> responseType) {
        return new RestTemplate().getForEntity(uri, responseType);
    }


    public void insertDataFromJsonToDatabase() {
        ResponseEntity<UserDTO[]> response = callToRemoteEndpoint(URI, UserDTO[].class);
        // TODO add logging here (status code and body)
        validateResponse(response);
        UserDTO[] responseBody = response.getBody();
        Arrays.stream(Objects.requireNonNull(responseBody))
                .forEach(user -> {
                    geoService.insertGeoToDatabase(user.getAddress().getGeo());
                    addressService.insertAddressToDatabase(user.getAddress(), user.getAddress().getGeo());
                    companyService.insertCompanyToDatabase(user.getCompany());
                    insertUserToDatabase(user, user.getAddress(), user.getCompany());
                });
    }
    private void insertUserToDatabase(final UserDTO userDTO, final AddressDTO addressDTO, final CompanyDTO companyDTO) {
        UserModel user = convertUserDTOToUser(userDTO, addressDTO, companyDTO);
        userRepository.save(user);
    }

    private UserModel convertUserDTOToUser(UserDTO userDTO, AddressDTO addressDTO, CompanyDTO companyDTO) {
        return UserModel.builder()
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .phone(userDTO.getName())
                .username(userDTO.getUserName())
                .website(userDTO.getWebsite())
                .address(addressService.convertAddressDTOToAddress(addressDTO, geoService.convertGeoDTOToGEO(addressDTO.getGeo())))
                .company(companyService.convertCompanyDTOToCompany(companyDTO))
                .build();
    }

    private void validateResponse(final ResponseEntity<UserDTO[]> response) {
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            // TODO add custom exception
            throw new RuntimeException(String.format("unexpected status code -> %s", response.getStatusCode().value()));
        }
        if (response.getBody() == null) {
            // TODO add custom exception
            throw new RuntimeException("The received response does not contain a body");
        }
    }
}
