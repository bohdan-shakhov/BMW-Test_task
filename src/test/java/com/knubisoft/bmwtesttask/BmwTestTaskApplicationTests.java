package com.knubisoft.bmwtesttask;

import com.knubisoft.bmwtesttask.controller.impl.UserAPIImpl;
import com.knubisoft.bmwtesttask.db_model.UserModel;
import com.knubisoft.bmwtesttask.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BmwTestTaskApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserAPIImpl userAPI;

    @Test
    void userControllerLoadsTest() {
        assertThat(userAPI).isNotNull();
    }

    @Test
    void checkThatStatusCodeOfExternalEndpoint200() throws IOException { // TODO try catch
        URL obj = new URL("https://jsonplaceholder.typicode.com/users");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        assertEquals(con.getResponseCode(), HttpStatus.OK.value());
    }

    @Test
    void checkThatStatusOfInternalEndpoint200() throws Exception {
        this.mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk());
    }

    @Test
    void checkThatResponseOfExternalEndpointReturnTenUsers() {
        ResponseEntity<UserDTO[]> response = new RestTemplate()
                .getForEntity("https://jsonplaceholder.typicode.com/users", UserDTO[].class);
        UserDTO[] responseBody = response.getBody();
        assertEquals(10, responseBody.length);
    }

    @Test
    void checkThatResponseOfInternalEndpointReturnTenUsers() throws Exception {
        this.mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(10))
                .andReturn();
    }

    @Test
    void checkThatAfterTenCalsToExternalEndpointReceivedHundredUsers() {
        List<UserDTO> actualList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ResponseEntity<UserDTO[]> response = new RestTemplate()
                    .getForEntity("https://jsonplaceholder.typicode.com/users", UserDTO[].class);
            UserDTO[] responseBody = response.getBody();
            List<UserDTO> tempList = Arrays.asList(responseBody);
            actualList.addAll(tempList);
        }
        assertEquals(100, actualList.size());
    }

    @Test
    void checkThatAfterTenCalsToInternalEndpointReceivedHundredUsers() {
        List<UserModel> actualList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ResponseEntity<List> response = new RestTemplate()
                    .getForEntity("https://jsonplaceholder.typicode.com/users", List.class);
            List body = response.getBody();
            actualList.addAll(body);
        }
        assertEquals(100, actualList.size());
    }

}
