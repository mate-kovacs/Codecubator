package com.codecool.poop.controller;

import com.codecool.poop.model.User;
import com.codecool.poop.model.assignments.Assignment;
import com.codecool.poop.model.assignments.coding.CodingQuestion;
import com.codecool.poop.model.assignments.quiz.QuizAnswer;
import com.codecool.poop.model.assignments.quiz.QuizQuestion;
import com.codecool.poop.service.AssignmentUtilityService;
import com.codecool.poop.service.SessionService;
import com.codecool.poop.service.coding_services.CodingAssignmentService;
import com.codecool.poop.service.coding_services.CodingQuestionService;
import com.codecool.poop.service.quiz_services.QuizAssignmentService;
import com.codecool.poop.service.quiz_services.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class APIAssignmentController {

    @Autowired
    private CodingAssignmentService codingAssignmentService;
    @Autowired
    private CodingQuestionService codingQuestionService;
    @Autowired
    private QuizAssignmentService quizAssignmentService;
    @Autowired
    private QuizQuestionService quizQuestionService;
    @Autowired
    private AssignmentUtilityService utility;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/coding-assignment", method = RequestMethod.POST)
    public ResponseEntity<String> nextCodingQuetion(@RequestParam("assignment_id") Integer assignmentID,
                                                    @RequestParam("question_id") Integer questionID) {
        List<CodingQuestion> questionList = codingAssignmentService.getCodingAssignmentById(assignmentID).getQuestions();

        if (utility.isLastCodingQuestion(questionID, questionList)) {
            Assignment assignment = codingAssignmentService.getCodingAssignmentById(assignmentID);
            Map<String, Object> assignmentEvaluation = utility.getAssignmentEvaluation(assignment);
            return new ResponseEntity(assignmentEvaluation, HttpStatus.OK);
        } else {
            Map<String, Object> nextQuestionData = utility.getNextCodingQuestionData(questionID, questionList);
            return new ResponseEntity(nextQuestionData, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/quiz-assignment", method = RequestMethod.POST)
    public ResponseEntity<String> nextQuizQuetion(@RequestParam("assignment_id") Integer assignmentID,
                                                  @RequestParam("question_id") Integer questionID) {
        List<QuizQuestion> questionList = quizAssignmentService.getQuizAssignmentById(assignmentID).getQuestions();

        if (utility.isLastQuizQuestion(questionID, questionList)) {
            Assignment assignment = quizAssignmentService.getQuizAssignmentById(assignmentID);
            Map<String, Object> assignmentEvaluation = utility.getAssignmentEvaluation(assignment);
            return new ResponseEntity(assignmentEvaluation, HttpStatus.OK);
        } else {
            Map<String, Object> nextQuestionData = utility.getNextQuizQuestionData(questionID, questionList);
            return new ResponseEntity(nextQuestionData, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/coding-answer", method = RequestMethod.POST)
    public ResponseEntity<String> evaluateCodingAnswer(@RequestParam("answers[]") String answers[],
                                                       @RequestParam("question_id") Integer questionID) {

        List<String> answerTexts = new ArrayList<>(Arrays.asList(answers));
        User user = sessionService.getCurrentUser();


        CodingQuestion question = codingQuestionService.getCodingQuestionById(questionID);
        int numberOfCorrectAnswers = question.checkSolution(answerTexts);
        boolean correctAnswer = numberOfCorrectAnswers == question.getMaxPoints();
        boolean death = utility.isUserDead(user, correctAnswer);

        utility.savePointsToSession(numberOfCorrectAnswers);

        Map<String, Object> answerEvaluation = utility.getAnswerEvaluation(correctAnswer, death);
        return new ResponseEntity(answerEvaluation, HttpStatus.OK);
    }

    @RequestMapping(value = "/quiz-answer", method = RequestMethod.POST)
    public ResponseEntity<String> evaluateQuizAnswer(@RequestParam("answers[]") String answers[],
                                                     @RequestParam("question_id") Integer questionID) {

        List<String> answerTexts = new ArrayList<>(Arrays.asList(answers));
        User user = sessionService.getCurrentUser();


        QuizQuestion question = quizQuestionService.getQuizQuestionById(questionID);
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

        utility.savePointsToSession(numberOfCorrectAnswers);

        Map<String, Object> answerEvaluation = utility.getAnswerEvaluation(correctAnswer, death);
        return new ResponseEntity(answerEvaluation, HttpStatus.OK);
    }

}
