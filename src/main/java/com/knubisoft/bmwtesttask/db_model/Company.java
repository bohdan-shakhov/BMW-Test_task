package com.knubisoft.bmwtesttask.db_model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bs;

    private String catchPhrase;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<UserModel> users;

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", bs='" + bs + '\'' +
                ", catchPhrase='" + catchPhrase + '\'' +
                ", name='" + name +
                '}';
    }
}
