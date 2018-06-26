package com.codecool.poop.service;

import com.codecool.poop.model.User;
import com.codecool.poop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean saveUser(User entity) {
        try {
            userRepository.save(entity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
