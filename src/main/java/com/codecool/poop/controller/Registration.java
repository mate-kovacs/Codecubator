package com.codecool.poop.controller;

import com.codecool.poop.ORM.UserManager;
import com.codecool.poop.config.TemplateEngineUtil;

import com.codecool.poop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet(urlPatterns = {"/registration"})
public class Registration extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        engine.process("registration.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String hashPW = BCrypt.hashpw(password, BCrypt.gensalt(12));
        String email = request.getParameter("email");
        User user = new User(name, hashPW, email);
        UserManager userManager = new UserManager();
        if (userManager.registerUser(user)) {
            System.out.println("User registered");
        } else {
            System.out.println("Name or email already in use");
        }
    }
}
