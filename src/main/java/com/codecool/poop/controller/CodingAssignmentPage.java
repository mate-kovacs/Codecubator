package com.codecool.poop.controller;

import com.codecool.poop.config.TemplateEngineUtil;
import com.codecool.poop.dao.CodingQuestManager;
import com.codecool.poop.model.assignments.coding.CodingAnswer;
import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.model.assignments.coding.CodingQuestion;
import org.json.JSONObject;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

        context.setVariable("assignment", assignment);

        engine.process("assignments/coding_assignment.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session;
        session = request.getSession();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        if (!isUserLoggedIn(session)) {
            response.sendRedirect("/");
            return;
        }

        int assignmentID = Integer.parseInt(request.getParameter("assignment_id"));
        int questionID = Integer.parseInt(request.getParameter("question_id"));

        CodingAssignment assignment = manager.getCodingAssignemntByID(assignmentID);
        CodingQuestion question = manager.getCodingQuestionByID(questionID);
        if (assignment == null) {
            System.out.println("No coding assignment with such ID.");
            response.sendRedirect("/");
            return;
        } else if (question == null) {
            if (questionID == 0) {
                System.out.println("First question");

                List<CodingQuestion> questionList = assignment.getQuestions();
                CodingQuestion nextQuestion = questionList.get(0);

                JSONObject nextQuestionData = new JSONObject();
                nextQuestionData.put("question_id", nextQuestion.getId());
                nextQuestionData.put("question_text", nextQuestion.getQuestion());

                List<Integer> answerIdList = new ArrayList<>();
                for (CodingAnswer answer : nextQuestion.getAnswers()) {
                    answerIdList.add(answer.getId());
                }

                nextQuestionData.put("answer_ids", answerIdList);
                response.setContentType("application/json");
                response.getWriter().print(nextQuestionData);
                return;
            }

        } else {
            System.out.println("No coding question with such ID.");
            response.sendRedirect("/");
            return;
        }


        CodingQuestion nextQuestion = null;
        List<CodingQuestion> questionList = assignment.getQuestions();
        try {
            for (CodingQuestion currentQuestion : questionList) {
                if (currentQuestion.getId() == questionID) {
                    nextQuestion = questionList.get(questionList.indexOf(currentQuestion) + 1);
                }
            }

            System.out.println("Return the next qustion");

            JSONObject nextQuestionData = new JSONObject();
            nextQuestionData.put("question_id", nextQuestion.getId());
            nextQuestionData.put("question_text", nextQuestion.getQuestion());

            List<Integer> answerIdList = new ArrayList<>();
            for (CodingAnswer answer : nextQuestion.getAnswers()) {
                answerIdList.add(answer.getId());


                nextQuestionData.put("answer_ids", answerIdList);
                response.setContentType("application/json");
                response.getWriter().print(nextQuestionData);
            }

        } catch (IndexOutOfBoundsException ex) {
            response.setContentType("text/plain");
            response.getWriter().print("Last question");

            System.out.println("That was the last question");
        }


    }

}
