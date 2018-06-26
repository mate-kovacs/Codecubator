package com.codecool.poop.dao;

import com.codecool.poop.db_initializer.DummyDBInitializer;
import com.codecool.poop.model.Skills;
import com.codecool.poop.model.assignments.coding.CodingAnswer;
import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.model.assignments.coding.CodingQuestion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CodingQuesttManagerTest {

    private static int testAssignmentID;
    private static int testQuestionID;
    private static int testAnswerID;

    @BeforeAll
    private static void setup() {
        UserManager userManager = new UserManager();
        CodingQuestManager codingQuestManager = new CodingQuestManager();
        QuizQuestManager quizQuestManager = new QuizQuestManager();
        MasteryQuestManager masteryQuestManager = new MasteryQuestManager();

        DummyDBInitializer dummyDBInitializer = new DummyDBInitializer(
                userManager,
                quizQuestManager,
                codingQuestManager,
                masteryQuestManager);
        dummyDBInitializer.initializeDummyDB();

        testAssignmentID = 1;
        testAnswerID = 8;
        testQuestionID = 1;

    }

    @Test
    void test_get_coding_assignment_happy_path() {

        CodingQuestManager manager = new CodingQuestManager();
        CodingAssignment assignment = manager.getCodingAssignemntByID(testAssignmentID);

        assertEquals("Number one", assignment.getName());
    }

    @Test
    void test_get_coding_assignment_invalid_id() {

        CodingQuestManager manager = new CodingQuestManager();
        assertThrows(NullPointerException.class, () -> manager.getCodingAssignemntByID(0).getName());
    }

    @Test
    void test_get_coding_question_happy_path() {

        CodingQuestManager manager = new CodingQuestManager();
        CodingQuestion question = manager.getCodingQuestionByID(testQuestionID);

        assertEquals("What?", question.toString());
    }

    @Test
    void test_get_coding_question_invalid_id() {

        CodingQuestManager manager = new CodingQuestManager();
        assertThrows(NullPointerException.class, () -> manager.getCodingQuestionByID(0).toString());
    }

    @Test
    void test_get_coding_answer_happy_path() {

        CodingQuestManager manager = new CodingQuestManager();
        CodingAnswer answer = manager.getCodingAnswerByID(testAnswerID);

        assertEquals("Me", answer.getAnswer());

    }

    @Test
    void test_get_coding_answer_invalid_id() {

        CodingQuestManager manager = new CodingQuestManager();
        assertThrows(NullPointerException.class, () -> manager.getCodingAnswerByID(0).toString());
    }

    @Test
    void test_get_all_coding_assignments_happy_path() {

        CodingQuestManager manager = new CodingQuestManager();
        assertEquals(2, manager.getAllCodingAssignments().size());
    }

    @Test
    void test_get_all_coding_questions_happy_path() {

        CodingQuestManager manager = new CodingQuestManager();
        assertEquals(6, manager.getAllCodingQuestions().size());
    }

}