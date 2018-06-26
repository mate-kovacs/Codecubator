package com.codecool.poop.controller;

import com.codecool.poop.dao.CodingQuestManager;
import com.codecool.poop.dao.QuizQuestManager;
import com.codecool.poop.dao.UserManager;
import com.codecool.poop.model.assignments.Assignment;
import com.codecool.poop.model.assignments.coding.CodingQuestion;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class APIAssignmentController {

    @Autowired
    private CodingQuestManager codingQuestManager;
    @Autowired
    private QuizQuestManager quizQuestManager;
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

}
