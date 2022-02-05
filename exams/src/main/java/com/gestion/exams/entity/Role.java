package com.gestion.exams.entity;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id ;

    @Column(unique=true)
    String name ;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
