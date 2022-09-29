package com.knubisoft.bmwtesttask.dto;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String website;

    @OneToOne
    private Address address;

    private String phone;

    private String name;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    private String email;

    private String username;
}