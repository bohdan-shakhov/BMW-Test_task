package com.knubisoft.bmwtesttask.controller.impl;

import com.knubisoft.bmwtesttask.controller.spec.UserAPI;
import com.knubisoft.bmwtesttask.db_model.UserModel;
import com.knubisoft.bmwtesttask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserAPIImpl implements UserAPI {

    private final UserService userService;

    @Override
    public ResponseEntity<List<UserModel>> getSearchHints() {
        List<UserModel> users = userService.getAllUsers();
        ResponseEntity<List<UserModel>> responseEntity = new ResponseEntity<>(users, HttpStatus.OK);
        userService.validateResponseFromInternalEndpoint(responseEntity);
        return responseEntity;
    }
}
