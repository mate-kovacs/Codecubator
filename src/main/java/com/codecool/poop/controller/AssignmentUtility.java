package com.codecool.poop.controller;

import com.codecool.poop.dao.UserManager;
import com.codecool.poop.model.User;
import com.codecool.poop.model.assignments.Assignment;
import com.codecool.poop.model.assignments.coding.CodingAnswer;
import com.codecool.poop.model.assignments.coding.CodingQuestion;
import com.codecool.poop.model.assignments.quiz.QuizAnswer;
import com.codecool.poop.model.assignments.quiz.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssignmentUtility {

    @Autowired
    private UserManager userManager;

    public Map<String, Object> getAssignmentEvaluation(Assignment assignment, HttpSession session) {

        Map<String, Object> userData = (Map) session.getAttribute("user");
        User user = userManager.getUserByName(userData.get("user_name").toString());
        userManager.addRewardToUser(user, assignment);

        Map<String, Object> assignmentEvaluation = new HashMap<>();
        assignmentEvaluation.put("points_achieved", session.getAttribute("points").toString());
        assignmentEvaluation.put("max_points", ((Integer) assignment.getMaxPoints()).toString());
        return assignmentEvaluation;
    }

    public Map<String, Object> getNextCodingQuestionData(int questionID, List<CodingQuestion> questionList) {
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

        List<Integer> answerIdList = new ArrayList<>();
        for (CodingAnswer answer : nextQuestion.getAnswers()) {
            answerIdList.add(answer.getId());
        }
        Map<String, Object> nextQuestionData = new HashMap<>();
        nextQuestionData.put("question_id", nextQuestion.getId());
        nextQuestionData.put("question_text", nextQuestion.getQuestion());
        nextQuestionData.put("answer_ids", answerIdList);

        return nextQuestionData;
    }

    public Map<String, Object> getNextQuizQuestionData(int questionID, List<QuizQuestion> questionList) {
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

        List<Integer> answerIdList = new ArrayList<>();
        for (QuizAnswer answer : nextQuestion.getQuizAnswers()) {
            answerIdList.add(answer.getId());
        }
        Map<String, Object> nextQuestionData = new HashMap<>();
        nextQuestionData.put("question_id", nextQuestion.getId());
        nextQuestionData.put("question_text", nextQuestion.getQuestionText());
        nextQuestionData.put("answer_ids", answerIdList);

        return nextQuestionData;
    }

    public Map<String, Object> getAnswerEvaluation(boolean correctAnswer, boolean death) {
        Map<String, Object> evaluationData = new HashMap<>();

        evaluationData.put("death", death);
        evaluationData.put("correct_answer", correctAnswer);
        return evaluationData;
    }

    public boolean isFirstQuestion(int questionID) {
        return questionID == 0;
    }

    public boolean isLastCodingQuestion(int questionID, List<CodingQuestion> questionList) {
        return questionList.get(questionList.size() - 1).getId() == questionID;
    }

    public boolean isLastQuizQuestion(int questionID, List<QuizQuestion> questionList) {
        return questionList.get(questionList.size() - 1).getId() == questionID;
    }

    public boolean isUserDead(User user, boolean correctAnswer) {
        boolean death = false;
        if (!correctAnswer) {
            user.loseOneHealth();
            if (user.getHealth() == 0) {
                death = true;
            }
        }
        return death;
    }

    public void savePointsToSession(HttpSession session, int numberOfCorrectAnswers) {
        Integer currentPoints = (Integer) session.getAttribute("points");
        if (currentPoints == null) {
            currentPoints = 0;
        }
        session.setAttribute("points", currentPoints + numberOfCorrectAnswers);
    }
}
