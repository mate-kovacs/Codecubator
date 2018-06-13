package com.codecool.poop.controller;

import com.codecool.poop.config.TemplateEngineUtil;
import com.codecool.poop.dao.QuizQuestManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class QuizAssignmentsController extends HttpServlet implements LoginHandler {

    private QuizQuestManager quizQuestManager;

    public QuizAssignmentsController(QuizQuestManager quizQuestManager) {
        this.quizQuestManager = quizQuestManager;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        HttpSession session = request.getSession();
        if (!isUserLoggedIn(session)) {
            response.sendRedirect("/");
            return;
        }
        Map userData = (Map) session.getAttribute("user");

        List quizAssignments = quizQuestManager.getAllQuizAssignments();
        System.out.println(quizAssignments);

        context.setVariable("user_name", userData.get("user_name"));
        context.setVariable("assignments", quizAssignments);
        engine.process("quiz_assignments.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Post received");
    }
}
