package com.codecool.poop.controller;

import com.codecool.poop.config.TemplateEngineUtil;
import com.codecool.poop.dao.QuizQuestManager;
import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class QuizAssignmentController extends HttpServlet implements LoginHandler {
    private QuizQuestManager quizQuestManager;

    public QuizAssignmentController(QuizQuestManager quizQuestManager) {
        this.quizQuestManager = quizQuestManager;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session;
        session = request.getSession();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        if (!isUserLoggedIn(session)) {
            response.sendRedirect("/");
            return;
        }
        Integer assignmentID = Integer.parseInt(request.getParameter("assignment_id"));
        context.setVariable("assignment_id", assignmentID);
        engine.process("quiz_assignment/quiz_assignment.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Post received");
        Integer assignmentID = Integer.parseInt(request.getParameter("assignment_id"));
        QuizAssignment quizAssignment = quizQuestManager.getQuizAssignemntByID(assignmentID);
    }
}
