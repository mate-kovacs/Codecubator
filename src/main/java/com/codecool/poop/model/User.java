package com.codecool.poop.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    private static final int maxHealth = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private int codeCoins;
    private LocalDateTime registrationDate;

    @CollectionTable(name = "users_skills")
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyEnumerated(EnumType.STRING)
    private Map<Skills,Integer> experiences = new HashMap<>();

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Achievement> achievements = new HashSet<>();

    @Transient
    private int health;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        registrationDate = LocalDateTime.now();
        this.initExperiences();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCodeCoins() {
        return codeCoins;
    }

    public void setCodeCoins(int codeCoins) {
        this.codeCoins = codeCoins;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Map<Skills, Integer> getExperiences() {
        return experiences;
    }

    public void setExperiences(Map<Skills, Integer> skills) {
        experiences = skills;
    }

    public void addXpValueToSkill(Skills skill, Integer value) {
        experiences.put(skill, experiences.get(skill) + value);
    }

    public Set<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(Set<Achievement> achievements) {
        this.achievements = achievements;
    }

    public void addAchievement(Achievement achievement) {
        this.achievements.add(achievement);
    }

    public void setHealthToMax(){
        health = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void loseOneHealth(){
        health --;
    }

    private void initExperiences() {
        for (Skills skill : Skills.values()) {
            experiences.put(skill, 0);
        }
    }
}
