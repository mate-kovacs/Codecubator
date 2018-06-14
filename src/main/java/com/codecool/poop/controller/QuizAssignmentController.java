package com.codecool.poop.controller;

import com.codecool.poop.config.TemplateEngineUtil;
import com.codecool.poop.dao.QuizQuestManager;
import com.codecool.poop.dao.UserManager;
import com.codecool.poop.model.assignments.coding.CodingAnswer;
import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.model.assignments.coding.CodingQuestion;
import com.codecool.poop.model.assignments.quiz.QuizAnswer;
import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import com.codecool.poop.model.assignments.quiz.QuizQuestion;
import org.json.JSONObject;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizAssignmentController extends HttpServlet implements LoginHandler {
    private QuizQuestManager quizQuestManager;
    private UserManager userManager;

    public QuizAssignmentController(QuizQuestManager quizQuestManager, UserManager userManager) {
        this.quizQuestManager = quizQuestManager;
        this.userManager = userManager;
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

        setAchievedPointsToZero(session);
        Integer assignmentID = Integer.parseInt(request.getParameter("assignment_id"));
        context.setVariable("assignment_id", assignmentID);
        engine.process("quiz_assignment/quiz_assignment.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session;
        session = request.getSession();
        if (!isUserLoggedIn(session)) {
            response.sendRedirect("/");
            return;
        }

        System.out.println("Post received");
        Integer assignmentID = Integer.parseInt(request.getParameter("assignment_id"));
        Integer questionID = Integer.parseInt(request.getParameter("question_id"));

        if (isAnswerSubmitted(request)) {
            String answers[] = request.getParameterValues("answers[]");
            List<String> answerTexts = new ArrayList<>(Arrays.asList(answers));

            QuizQuestion question = quizQuestManager.getQuizQuestionByID(questionID);
            List<QuizAnswer> correctAnswerList = question.getQuizAnswers();
            int numberOfCorrectAnswers = 0;
            for (int i = 0; i < correctAnswerList.size(); i++) {
                if (Boolean.parseBoolean(answers[i]) == correctAnswerList.get(i).getAnswerValidity()){
                    if(correctAnswerList.get(i).getAnswerValidity()){
                        numberOfCorrectAnswers ++;
                    }
                }
            }
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

        List<QuizQuestion> questionList = quizQuestManager.getQuizAssignemntByID(assignmentID).getQuestions();

        if (isLastQuestion(questionID, questionList)) {

            JSONObject assignmentEvaluation = createJsonAssignmentEvaluation(session, assignmentID);

            response.setContentType("application/json");
            response.getWriter().print(assignmentEvaluation);
            return;
        }

        QuizQuestion nextQuestion = null;

        if (isFirstQuestion(questionID)) {
            nextQuestion = questionList.get(0);
        } else {
            for (QuizQuestion currentQuestion : questionList) {
                if (currentQuestion.getId() == questionID) {
                    nextQuestion = questionList.get(questionList.indexOf(currentQuestion) + 1);
                }
            }
        }

        JSONObject nextQuestionData = createJsonNextQuestionData(nextQuestion);

        response.setContentType("application/json");
        response.getWriter().print(nextQuestionData);
    }

    private void savePointsToSession(HttpSession session, int numberOfCorrectAnswers) {
        int pointsForThisQuestion = numberOfCorrectAnswers;
        Integer currentPoints = (Integer) session.getAttribute("points");
        if (currentPoints == null) {
            currentPoints = 0;
        }
        session.setAttribute("points", currentPoints + pointsForThisQuestion);
    }

    private JSONObject createJsonAnswerEvaluation(boolean correctAnswer) {
        JSONObject evaluationData = new JSONObject();

        evaluationData.put("correct_answer", correctAnswer);
        return evaluationData;
    }

    private JSONObject createJsonNextQuestionData(QuizQuestion nextQuestion) {
        JSONObject nextQuestionData = new JSONObject();
        nextQuestionData.put("question_id", nextQuestion.getId());
        nextQuestionData.put("question_text", nextQuestion.getQuestionText());

        List<String> answerList = new ArrayList<>();
        for (QuizAnswer answer : nextQuestion.getQuizAnswers()) {
            answerList.add(answer.getAnswerText());
        }
        nextQuestionData.put("answers", answerList);
        return nextQuestionData;
    }

    private JSONObject createJsonAssignmentEvaluation(HttpSession session, int assignmentID) {
        JSONObject evaluationData = new JSONObject();

        evaluationData.put("points_achieved", session.getAttribute("points"));
        evaluationData.put("max_points", quizQuestManager.getQuizAssignemntByID(assignmentID).getMaxPoint());
        return evaluationData;
    }

    private boolean isAssignmentValid(int assignmentID) {
        QuizAssignment assignment = quizQuestManager.getQuizAssignemntByID(assignmentID);
        return assignment != null;
    }

    private boolean isQuestionValid(int questionID) {
        if (questionID == 0) {
            return true;
        }
        QuizQuestion question = quizQuestManager.getQuizQuestionByID(questionID);
        return question != null;
    }

    private boolean isFirstQuestion(int questionID) {
        return questionID == 0;
    }

    private boolean isLastQuestion(int questionID, List<QuizQuestion> questionList) {
        return questionList.get(questionList.size() - 1).getId() == questionID;
    }

    private boolean isAnswerSubmitted(HttpServletRequest request) {
        return request.getParameter("answers[]") != null;
    }

    private boolean isWholeAnswerCorrect(QuizQuestion question, int numberOfCorrectAnswers) {
        return numberOfCorrectAnswers == question.getMaxPoints();
    }

    private void setAchievedPointsToZero(HttpSession session) {
        session.setAttribute("points", 0);
    }
}
