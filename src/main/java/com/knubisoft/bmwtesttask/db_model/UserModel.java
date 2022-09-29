package com.knubisoft.bmwtesttask.db_model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String website;

    @OneToOne(cascade=CascadeType.ALL)
    private Address address;

    private String phone;

    private String name;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="company_id")
    private Company company;

    private String email;

    private String username;
}