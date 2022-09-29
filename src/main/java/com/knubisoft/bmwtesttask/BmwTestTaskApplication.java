package com.knubisoft.bmwtesttask;

import com.knubisoft.bmwtesttask.service.AddressService;
import com.knubisoft.bmwtesttask.service.CompanyService;
import com.knubisoft.bmwtesttask.service.GeoService;
import com.knubisoft.bmwtesttask.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BmwTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(BmwTestTaskApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserService userService) {
        return args -> userService.insertDataFromJsonToDatabase();
    }
}
