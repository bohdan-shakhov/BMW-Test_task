package com.knubisoft.bmwtesttask.db_model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String zipcode;

    @OneToOne
    private Geo geo;

    private String suite;

    private String city;

    private String street;
}
