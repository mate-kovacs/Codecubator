package com.codecool.poop.controller;

import com.codecool.poop.dao.UserManager;
import com.codecool.poop.config.TemplateEngineUtil;

import com.codecool.poop.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.persistence.NoResultException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Login extends HttpServlet {
    private UserManager userManager;

    public Login(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        engine.process("login.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        try {
            User user = userManager.getUserByName(name);
            if (BCrypt.checkpw(password, user.getPassword())) {
                HttpSession session;
                session = request.getSession();
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("user_name", user.getUsername());
                userMap.put("user_id", user.getId());
                session.setAttribute("user", userMap);
                System.out.println("User logged in!");
            } else {
                System.out.println("Passwords not matching");
                response.setContentType("text/plain");
                response.getWriter().print("Not matching");
            }
        } catch (NoResultException e) {
            System.out.println("No such username");
            response.setContentType("text/plain");
            response.getWriter().print("Not matching");
        }
    }
}


