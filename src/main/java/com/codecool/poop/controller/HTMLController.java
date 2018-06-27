package com.codecool.poop.controller;


import com.codecool.poop.model.Skills;
import com.codecool.poop.model.User;
import com.codecool.poop.service.SessionService;
import com.codecool.poop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;


@Controller
public class HTMLController implements LoginHandler{

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping(value = "/registration")
    public String registrationPage() {
        return "registration";
    }

    @GetMapping(value = "/")
    public String indexPage(Model model) {
        if (sessionService.getCurrentUser() == null) {
            return "redirect:/login";
        }
        model.addAttribute("user_name", sessionService.getCurrentUser().getUsername());
        return "index";
    }

    @GetMapping(value = "/user-profile")
    public String userProfilePage(Model model) {
        if (sessionService.getCurrentUser() == null) {
            return "redirect:/login";
        }
        User user = sessionService.getCurrentUser();
        Map<Skills, Integer> skills = user.getExperiences();
        model.addAttribute("user_name", user.getUsername());
        model.addAttribute("skills", skills);
        return "user_profile";
    }
}
