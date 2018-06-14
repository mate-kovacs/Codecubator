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
import java.util.Arrays;
import java.util.List;

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

        setAchievedPointsToZero(session);
        context.setVariable("assignment", assignment);

        engine.process("assignments/coding_assignment.html", context, response.getWriter());
    }

    private void setAchievedPointsToZero(HttpSession session) {
        session.setAttribute("points", 0);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session;
        session = request.getSession();
        if (!isUserLoggedIn(session)) {
            response.sendRedirect("/");
            return;
        }

        int assignmentID = Integer.parseInt(request.getParameter("assignment_id"));
        int questionID = Integer.parseInt(request.getParameter("question_id"));

        if (isAnswerSubmitted(request)) {
            String answers[] = (request.getParameterValues("answers[]"));
            List<String> answerTexts = new ArrayList<>(Arrays.asList(answers));

            CodingQuestion question = manager.getCodingQuestionByID(questionID);
            int numberOfCorrectAnswers = question.checkSolution(answerTexts);
            boolean correctAnswer = isWholeAnswerCorrect(question, numberOfCorrectAnswers);

            savePointsToSession(session, numberOfCorrectAnswers);

            JSONObject answerEvaluation = createJsonAnswerEvaluation(correctAnswer);

            response.setContentType("application/json");
            response.getWriter().print(answerEvaluation);
            return;
        }

        if (!isAssignmentValid(assignmentID)) {
            System.out.println("No coding assignment with such ID.");
            response.sendRedirect("/");
            return;
        }

        if (!isQuestionValid(questionID)) {
            System.out.println("No coding question with such ID.");
            response.sendRedirect("/");
            return;
        }

        List<CodingQuestion> questionList = manager.getCodingAssignemntByID(assignmentID).getQuestions();

        if (isLastQuestion(questionID, questionList)) {

            JSONObject assignmentEvaluation = createJsonAssignmentEvaluation(session, assignmentID);

            response.setContentType("application/json");
            response.getWriter().print(assignmentEvaluation);
            return;
        }

        CodingQuestion nextQuestion = null;

        if (isFirstQuestion(questionID)) {
            nextQuestion = questionList.get(0);
        } else {
            for (CodingQuestion currentQuestion : questionList) {
                if (currentQuestion.getId() == questionID) {
                    nextQuestion = questionList.get(questionList.indexOf(currentQuestion) + 1);
                }
            }
        }

        JSONObject nextQuestionData = createJsonNextQuestionData(nextQuestion);

        response.setContentType("application/json");
        response.getWriter().print(nextQuestionData);
    }

    private boolean isWholeAnswerCorrect(CodingQuestion question, int numberOfCorrectAnswers) {
        return numberOfCorrectAnswers == question.getMaxPoints();
    }

    private JSONObject createJsonAssignmentEvaluation(HttpSession session, int assignmentID) {
        JSONObject evaluationData = new JSONObject();

        evaluationData.put("points_achieved", session.getAttribute("points"));
        evaluationData.put("max_points", manager.getCodingAssignemntByID(assignmentID).getMaxPoints());
        return evaluationData;
    }

    private JSONObject createJsonAnswerEvaluation(boolean correctAnswer) {
        JSONObject evaluationData = new JSONObject();

        evaluationData.put("correct_answer", correctAnswer);
        return evaluationData;
    }

    private void savePointsToSession(HttpSession session, int numberOfCorrectAnswers) {
        int pointsForThisQuestion = numberOfCorrectAnswers;
        Integer currentPoints = (Integer) session.getAttribute("points");
        if (currentPoints == null){
            currentPoints = 0;
        }
        session.setAttribute("points", currentPoints + pointsForThisQuestion);
    }

    private JSONObject createJsonNextQuestionData(CodingQuestion nextQuestion) {
        JSONObject nextQuestionData = new JSONObject();
        nextQuestionData.put("question_id", nextQuestion.getId());
        nextQuestionData.put("question_text", nextQuestion.getQuestion());

        List<Integer> answerIdList = new ArrayList<>();
        for (CodingAnswer answer : nextQuestion.getAnswers()) {
            answerIdList.add(answer.getId());
        }
        nextQuestionData.put("answer_ids", answerIdList);
        return nextQuestionData;
    }

    private boolean isAssignmentValid(int assignmentID) {
        CodingAssignment assignment = manager.getCodingAssignemntByID(assignmentID);
        return assignment != null;
    }

    private boolean isQuestionValid(int questionID) {
        if (questionID == 0) {
            return true;
        }
        CodingQuestion question = manager.getCodingQuestionByID(questionID);
        return question != null;
    }

    private boolean isFirstQuestion(int questionID) {
        return questionID == 0;
    }

    private boolean isLastQuestion(int questionID, List<CodingQuestion> questionList) {
        return questionList.get(questionList.size() - 1).getId() == questionID;
    }

    private boolean isAnswerSubmitted(HttpServletRequest request) {
        return request.getParameter("answers[]") != null;
    }

}
