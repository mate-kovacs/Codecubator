package com.codecool.poop.controller;

import com.codecool.poop.ORM.UserManager;
import com.codecool.poop.config.TemplateEngineUtil;

import com.codecool.poop.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(urlPatterns = {"/"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        engine.process("login.html", context, response.getWriter());
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        UserManager userManager = new UserManager();
        User user = userManager.getUserByName(name);
        HttpSession session;
        session = request.getSession();
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("user_name", user.getUsername());
        userMap.put("user_id", user.getId());
        if (BCrypt.checkpw(password, user.getPassword())) {
            session.setAttribute("user", userMap);
            System.out.println("User logged in!");
            response.sendRedirect("/index");
        }
        else
            System.out.println("It does not match");
    }
}


