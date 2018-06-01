package com.codecool.poop.controller;

import com.codecool.poop.dao.UserManager;
import com.codecool.poop.config.TemplateEngineUtil;

import com.codecool.poop.model.User;
import org.json.JSONObject;
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String hashPW = BCrypt.hashpw(password, BCrypt.gensalt(12));

        User user = new User(name, hashPW, email);
        UserManager userManager = new UserManager();
        JSONObject json = new JSONObject();
        response.setContentType("application/json");

        if (userManager.registerUser(user)) {
            json.put("isNameAddedToDB", true);
        } else {
            json.put("isNameAddedToDB", false);
            System.out.println("name already in use");
        }
        response.getWriter().print(json);
    }
}
