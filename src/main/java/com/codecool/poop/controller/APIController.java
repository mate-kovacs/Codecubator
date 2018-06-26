package com.codecool.poop.controller;

import com.codecool.poop.model.User;
import com.codecool.poop.service.SessionService;
import com.codecool.poop.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class APIController {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @PostMapping(value = "/login")
    public String userLogin(
            @RequestParam("name") String name,
            @RequestParam("password") String password
    ) {
        try {
            User user = userService.getUserByName(name);
            if (BCrypt.checkpw(password, user.getPassword())) {
                sessionService.setCurrentUser(user);
                System.out.println("User logged in!");
            } else {
                System.out.println("Passwords not matching");
                return "Not matching";

            }
        } catch (NullPointerException e) {
            System.out.println("No such username");
            return "Not matching";
        }
        return "success";
    }

    @PostMapping(value = "/registration")
    protected Map<String, Object> registerUser(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        String hashPW = BCrypt.hashpw(password, BCrypt.gensalt(12));

        User user = new User(name, hashPW, email);

        Map<String, Object> result = new HashMap<>();
        if (userService.saveUser(user)) {
            result.put("isNameAddedToDB", true);
        } else {
            result.put("isNameAddedToDB", false);
            System.out.println("name already in use");
        }
        return result;
    }
}
