package com.codecool.poop.controller;

import com.codecool.poop.dao.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class HTMLController implements LoginHandler{

    @Autowired
    private UserManager userManager;

    @GetMapping(value = "/")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registrationPage() {
        return "registration";
    }

    @GetMapping(value = "/index")
    public String indexPage(HttpServletRequest request, Model model) throws IOException {
        HttpSession session;
        session = request.getSession();
        if (!isUserLoggedIn(session)) {
            return "redirect:/";
        }
        model.addAttribute("user_name", "Pityu");
        return "index";
    }
}
