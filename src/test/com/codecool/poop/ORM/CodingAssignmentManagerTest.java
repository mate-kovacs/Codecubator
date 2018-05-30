package com.codecool.poop.ORM;

import com.codecool.poop.model.Skills;
import com.codecool.poop.model.assignments.coding.CodingAnswer;
import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.model.assignments.coding.CodingQuestion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CodingAssignmentManagerTest {

    private CodingAssignment assignment;
    private CodingQuestion question;
    private CodingAnswer answer;

    private void setup(){
        Map<Skills, Integer> reward = new HashMap<>();
        reward.put(Skills.HTML_BASIC, 5);
        reward.put(Skills.CSS_BASIC, 8);
        assignment = new CodingAssignment("Number one",
                "Description",
                reward,
                3);
        question = new CodingQuestion("Say 'what' again, motherfucker?!");
        answer = new CodingAnswer();
        List<String> answers = new ArrayList<>();
        answers.add("What");
        answers.add("is");
        answers.add("this");
        answers.add("bull shit");
        answer.setAnswers(answers);

        assignment.setQuestion(question);
        question.setAssignment(assignment);
        question.setAnswer(answer);
        answer.setQuestion(question);
    }

    @Test
    void test_get_coding_assignment_happy_path() {

        setup();

        CodingAssignmentManager manager = CodingAssignmentManager.getInstance();
        manager.addCodingAssignmentToDB(assignment, question, answer);
        CodingAssignment assignmentFromDB = manager.getCodingAssignemntByID(1);

        assertEquals(assignmentFromDB.getName(), assignment.getName());
    }

    @Test
    void test_get_coding_assignment_invalid_id() {

        setup();

        CodingAssignmentManager manager = CodingAssignmentManager.getInstance();
        manager.addCodingAssignmentToDB(assignment, question, answer);

        assertThrows(NullPointerException.class, () -> manager.getCodingAssignemntByID(0).getName());
    }

    @Test
    void test_get_coding_question_happy_path() {

        setup();

        CodingAssignmentManager manager = CodingAssignmentManager.getInstance();
        manager.addCodingAssignmentToDB(assignment, question, answer);
        CodingQuestion questionFromDB = manager.getCodingQuestionByID(1);

        assertEquals(questionFromDB.toString(), question.toString());
    }

    @Test
    void test_get_coding_question_invalid_id() {

        setup();

        CodingAssignmentManager manager = CodingAssignmentManager.getInstance();
        manager.addCodingAssignmentToDB(assignment, question, answer);

        assertThrows(NullPointerException.class, () -> manager.getCodingQuestionByID(0).toString());
    }

    @Test
    void test_get_coding_answer_happy_path() {

        setup();

        CodingAssignmentManager manager = CodingAssignmentManager.getInstance();
        manager.addCodingAssignmentToDB(assignment, question, answer);
        CodingAnswer answerfromDB = manager.getCodingAnswerByID(1);

        List<String> answersFromBD = answerfromDB.getAnswers();

        for (int i = 0; i < answer.getAnswers().size(); i++) {
            assertEquals(answerfromDB.getAnswers().get(i), answer.getAnswers().get(i));
        }
    }

    @Test
    void test_get_coding_answer_invalid_id() {

        setup();

        CodingAssignmentManager manager = CodingAssignmentManager.getInstance();
        manager.addCodingAssignmentToDB(assignment, question, answer);

        assertThrows(NullPointerException.class, () -> manager.getCodingAnswerByID(0).toString());
    }

}