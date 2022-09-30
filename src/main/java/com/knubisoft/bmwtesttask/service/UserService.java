package com.knubisoft.bmwtesttask.service;

import com.knubisoft.bmwtesttask.converter.UserConverter;
import com.knubisoft.bmwtesttask.db_model.UserModel;
import com.knubisoft.bmwtesttask.dto.AddressDTO;
import com.knubisoft.bmwtesttask.dto.CompanyDTO;
import com.knubisoft.bmwtesttask.dto.UserDTO;
import com.knubisoft.bmwtesttask.exception.ResponseWithoutBodyException;
import com.knubisoft.bmwtesttask.exception.WrongLengthOfResponseException;
import com.knubisoft.bmwtesttask.exception.WrongStatusCodeException;
import com.knubisoft.bmwtesttask.repository.UserModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
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
        validateResponseFromExternalEndpoint(response);
        UserDTO[] responseBody = response.getBody();
        Arrays.stream(Objects.requireNonNull(responseBody))
                .forEach(user -> {
                    insertUserToDatabase(user, user.getAddress(), user.getCompany());
                });
    }
    private void insertUserToDatabase(final UserDTO userDTO, final AddressDTO addressDTO, final CompanyDTO companyDTO) {
        UserModel user = UserConverter.convertUserDTOToUser(userDTO, addressDTO, companyDTO);
        userRepository.save(user);
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    private void validateResponseFromExternalEndpoint(final ResponseEntity<UserDTO[]> response) {
        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            throw new WrongStatusCodeException(String.format("unexpected status code -> %s", response.getStatusCode().value()));
        }
        if (response.getBody() == null) {
            throw new ResponseWithoutBodyException();
        }

        if (response.getBody().length != 10) {
            throw new WrongLengthOfResponseException();
        }
    }

    public void validateResponseFromInternalEndpoint(final ResponseEntity<List<UserModel>> responseEntity) {
        if (!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            throw new WrongStatusCodeException(String.format("unexpected status code -> %s", responseEntity.getStatusCode().value()));
        }
        if (responseEntity.getBody() == null) {
            throw new ResponseWithoutBodyException();
        }

        if (responseEntity.getBody().size() != 10) {
            throw new WrongLengthOfResponseException();
        }
    }
}
