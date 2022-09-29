package com.knubisoft.bmwtesttask.db_model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String zipcode;

    @OneToOne(cascade = CascadeType.ALL)
    private Geo geo;

    private String suite;

    private String city;

    private String street;
}
