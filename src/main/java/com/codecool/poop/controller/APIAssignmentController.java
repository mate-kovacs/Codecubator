package com.codecool.poop.controller;

import com.codecool.poop.dao.CodingQuestManager;
import com.codecool.poop.dao.QuizQuestManager;
import com.codecool.poop.dao.UserManager;
import com.codecool.poop.model.User;
import com.codecool.poop.model.assignments.Assignment;
import com.codecool.poop.model.assignments.coding.CodingQuestion;
import com.codecool.poop.model.assignments.quiz.QuizAnswer;
import com.codecool.poop.model.assignments.quiz.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class APIAssignmentController {

    @Autowired
    private CodingQuestManager codingQuestManager;
    @Autowired
    private QuizQuestManager quizQuestManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private AssignmentUtility utility;

    @RequestMapping(value = "/coding-assignment", method = RequestMethod.POST)
    public ResponseEntity<String> nextCodingQuetion(HttpServletRequest request,
                                              @RequestParam("assignment_id") Integer assignmentID,
                                              @RequestParam("question_id") Integer questionID) {
        HttpSession session = request.getSession();
        List<CodingQuestion> questionList = codingQuestManager.getCodingAssignemntByID(assignmentID).getQuestions();

        if (utility.isLastCodingQuestion(questionID, questionList)) {
            Assignment assignment = codingQuestManager.getCodingAssignemntByID(assignmentID);
            Map<String, Object> assignmentEvaluation = utility.getAssignmentEvaluation(assignment, session);
            return new ResponseEntity(assignmentEvaluation, HttpStatus.OK);
        } else {
            Map<String, Object> nextQuestionData = utility.getNextCodingQuestionData(questionID, questionList);
            return new ResponseEntity(nextQuestionData, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/quiz-assignment", method = RequestMethod.POST)
    public ResponseEntity<String> nextQuizQuetion(HttpServletRequest request,
                                              @RequestParam("assignment_id") Integer assignmentID,
                                              @RequestParam("question_id") Integer questionID) {
        HttpSession session = request.getSession();
        List<QuizQuestion> questionList = quizQuestManager.getQuizAssignemntByID(assignmentID).getQuestions();

        if (utility.isLastQuizQuestion(questionID, questionList)) {
            Assignment assignment = quizQuestManager.getQuizAssignemntByID(assignmentID);
            Map<String, Object> assignmentEvaluation = utility.getAssignmentEvaluation(assignment, session);
            return new ResponseEntity(assignmentEvaluation, HttpStatus.OK);
        } else {
            Map<String, Object> nextQuestionData = utility.getNextQuizQuestionData(questionID, questionList);
            return new ResponseEntity(nextQuestionData, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/coding-answer", method = RequestMethod.POST)
    public ResponseEntity<String> evaluateCodingAnswer(HttpServletRequest request,
                                              @RequestParam("answers[]") String answers[],
                                              @RequestParam("question_id") Integer questionID) {

        List<String> answerTexts = new ArrayList<>(Arrays.asList(answers));
        HttpSession session = request.getSession();
        Map<String, Object> userData = (Map) session.getAttribute("user");
        User user = userManager.getUserByName(userData.get("user_name").toString());


        CodingQuestion question = codingQuestManager.getCodingQuestionByID(questionID);
        int numberOfCorrectAnswers = question.checkSolution(answerTexts);
        boolean correctAnswer = numberOfCorrectAnswers == question.getMaxPoints();
        boolean death = utility.isUserDead(user, correctAnswer);

        utility.savePointsToSession(session, numberOfCorrectAnswers);

        Map<String, Object> answerEvaluation = utility.getAnswerEvaluation(correctAnswer, death);
        return new ResponseEntity(answerEvaluation, HttpStatus.OK);
    }

    @RequestMapping(value = "/quiz-answer", method = RequestMethod.POST)
    public ResponseEntity<String> evaluateQuizAnswer(HttpServletRequest request,
                                                    @RequestParam("answers[]") String answers[],
                                                    @RequestParam("question_id") Integer questionID) {

        List<String> answerTexts = new ArrayList<>(Arrays.asList(answers));
        HttpSession session = request.getSession();
        Map<String, Object> userData = (Map) session.getAttribute("user");
        User user = userManager.getUserByName(userData.get("user_name").toString());


        QuizQuestion question = quizQuestManager.getQuizQuestionByID(questionID);
        List<QuizAnswer> correctAnswerList = question.getQuizAnswers();
        int numberOfCorrectAnswers = 0;
        for (int i = 0; i < correctAnswerList.size(); i++) {
            if (Boolean.parseBoolean(answers[i]) == correctAnswerList.get(i).getAnswerValidity()) {
                if (correctAnswerList.get(i).getAnswerValidity()) {
                    numberOfCorrectAnswers++;
                }
            }
        }
        boolean correctAnswer = numberOfCorrectAnswers == question.getMaxPoints();
        boolean death = utility.isUserDead(user, correctAnswer);

        utility.savePointsToSession(session, numberOfCorrectAnswers);

        Map<String, Object> answerEvaluation = utility.getAnswerEvaluation(correctAnswer, death);
        return new ResponseEntity(answerEvaluation, HttpStatus.OK);
    }

}
