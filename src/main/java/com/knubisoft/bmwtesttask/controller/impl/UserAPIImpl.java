package com.knubisoft.bmwtesttask.controller.impl;

import com.knubisoft.bmwtesttask.controller.spec.UserAPI;
import com.knubisoft.bmwtesttask.db_model.UserModel;
import com.knubisoft.bmwtesttask.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserAPIImpl implements UserAPI {

    private final UserService userService;

    @Override
    public ResponseEntity<List<UserModel>> getSearchHints() {
        List<UserModel> users = userService.getAllUsers();
        ResponseEntity<List<UserModel>> responseEntity = new ResponseEntity<>(users, HttpStatus.OK);
        log.debug("Call to internal endpoint");
        log.debug("Status code of external api response - {}", responseEntity.getStatusCode());
        log.debug("Headers of external api response - {}", responseEntity.getHeaders());
        log.debug("Body of external api response - {}", responseEntity.getBody());
        userService.validateResponseFromInternalEndpoint(responseEntity);
        return responseEntity;
    }
}
