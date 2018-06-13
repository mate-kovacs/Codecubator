package com.codecool.poop.controller;

import com.codecool.poop.config.TemplateEngineUtil;
import com.codecool.poop.dao.CodingQuestManager;
import com.codecool.poop.model.assignments.coding.CodingAnswer;
import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.model.assignments.coding.CodingQuestion;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class CodingAssignmentPage extends HttpServlet implements LoginHandler {

    private CodingQuestManager manager;

    public CodingAssignmentPage(CodingQuestManager manager) {
        this.manager = manager;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session;
        session = request.getSession();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        if (!isUserLoggedIn(session)) {
            response.sendRedirect("/");
            return;
        }

        int assignmentID = Integer.parseInt(request.getParameter("assignment_id"));
        CodingAssignment assignment = manager.getCodingAssignemntByID(assignmentID);
        if (assignment == null) {
            System.out.println("No coding assignment with such ID.");
            response.sendRedirect("/");
            return;
        }

        context.setVariable("assignment_id", assignment.getId());
        context.setVariable("assignment_name", assignment.getName());
        context.setVariable("assignment_description", assignment.getDescription());
        int currentQuestionId = 0;
        for (CodingQuestion question : assignment.getQuestions()) {
            context.setVariable("question_" + currentQuestionId + "_id", question.getId());
            context.setVariable("question_" + currentQuestionId + "_question", question.getQuestion());
            int currentAnswerId = 0;
            for (CodingAnswer answer : question.getAnswers()) {
                context.setVariable("answer_" + currentAnswerId + "_id", answer.getId());
            }
        }

        engine.process("assignments/coding_assignment.html", context, response.getWriter());
    }

}
