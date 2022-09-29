package com.knubisoft.bmwtesttask.db_model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bs;

    private String catchPhrase;

    private String name;

    @OneToMany(mappedBy = "company")
    private List<UserModel> users;
}
