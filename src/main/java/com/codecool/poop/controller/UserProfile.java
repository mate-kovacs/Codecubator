//package com.codecool.poop.controller;
//
//import com.codecool.poop.config.TemplateEngineUtil;
//import com.codecool.poop.dao.UserManager;
//import com.codecool.poop.model.Skills;
//import com.codecool.poop.model.User;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.WebContext;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Map;
//
//public class UserProfile extends HttpServlet implements LoginHandler{
//    private UserManager userManager;
//
//    public UserProfile(UserManager userManager) {
//        this.userManager = userManager;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        HttpSession session;
//        session = request.getSession();
//        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
//        WebContext context = new WebContext(request, response, request.getServletContext());
//        if (!isUserLoggedIn(session)) {
//            response.sendRedirect("/");
//            return;
//        }
//        Map<String, Object> userData = (Map) session.getAttribute("user");
//        User user = userManager.getUserByName((String) userData.get("user_name"));
//        Map<Skills, Integer> skills = user.getExperiences();
//        context.setVariable("user_name", userData.get("user_name"));
//        context.setVariable("skills", skills);
//        engine.process("user_profile/user_profile.html", context, response.getWriter());
//    }
//}
