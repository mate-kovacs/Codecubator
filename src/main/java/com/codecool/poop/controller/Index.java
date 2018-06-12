package com.codecool.poop.controller;

import com.codecool.poop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class Index extends HttpServlet {

    public Index() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session;
        session = request.getSession();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        if (session.getAttribute("user") != null) {
            Map<String, Object> userData = (Map) session.getAttribute("user");
            context.setVariable("user_name", userData.get("user_name"));
            engine.process("index/index.html", context, response.getWriter());
        } else {
            response.sendRedirect("/");
        }
    }
}
