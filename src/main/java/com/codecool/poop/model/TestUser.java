package com.codecool.poop.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class TestUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    String name;

    public TestUser(String name) {
        this.name = name;
    }

    public TestUser() {
    }
}
