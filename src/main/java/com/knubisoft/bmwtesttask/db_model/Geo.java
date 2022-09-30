package com.knubisoft.bmwtesttask.db_model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "geo")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Geo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String lng;
    private String lat;

    @Override
    public String toString() {
        return "Geo{" +
                "id=" + id +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}