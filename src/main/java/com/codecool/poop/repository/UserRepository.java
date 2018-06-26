package com.codecool.poop.repository;

import com.codecool.poop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    public User findUserByUsername(String name);
}
