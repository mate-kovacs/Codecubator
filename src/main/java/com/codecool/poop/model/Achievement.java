package com.codecool.poop.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Achievement {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @ManyToMany
    private Set<User> users = new HashSet<>();

    private String description;
    private int codeCoinReward;

    protected Achievement() {
    }

    public Achievement(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Achievement(String name, String description, int codeCoinReward) {
        this.name = name;
        this.description = description;
        this.codeCoinReward = codeCoinReward;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCodeCoinReward() {
        return codeCoinReward;
    }

    public void setCodeCoinReward(int codeCoinReward) {
        this.codeCoinReward = codeCoinReward;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
    }
}
