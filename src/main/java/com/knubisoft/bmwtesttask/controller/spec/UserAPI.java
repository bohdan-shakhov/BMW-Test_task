package com.knubisoft.bmwtesttask.controller.spec;

import com.knubisoft.bmwtesttask.db_model.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/api/v1/users")
public interface UserAPI {

    @RequestMapping(
            produces = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<List<UserModel>> getSearchHints();
}
