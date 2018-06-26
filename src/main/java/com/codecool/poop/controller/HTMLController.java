package com.codecool.poop.controller;


import com.codecool.poop.service.SessionService;
import com.codecool.poop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


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
}
