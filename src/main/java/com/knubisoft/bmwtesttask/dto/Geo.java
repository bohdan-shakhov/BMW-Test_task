package com.knubisoft.bmwtesttask.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "geo")
@Getter
@Setter
public class Geo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String lng;
    private String lat;
}