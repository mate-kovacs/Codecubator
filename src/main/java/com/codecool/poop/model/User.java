package com.codecool.poop.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
public class User {

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
    private Date lastLogin;
    private LocalDateTime registrationDate;

//    @CollectionTable(name = "skills")
//    @ElementCollection
//    private List<Skills> skills = new ArrayList<>();

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Achievement> achievements = new HashSet<>();


    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        registrationDate = LocalDateTime.now();
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

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

//    public List<Skills> getSkills() {
//        return skills;
//    }
//
//    public void setSkills(List<Skills> skills) {
//        this.skills = skills;
//    }
//
//    public void addSkill(Skills skill) {
//        this.skills.add(skill);
//    }

    public Set<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(Set<Achievement> achievements) {
        this.achievements = achievements;
    }

    public void addAchievement(Achievement achievement) {
        this.achievements.add(achievement);
    }
}
