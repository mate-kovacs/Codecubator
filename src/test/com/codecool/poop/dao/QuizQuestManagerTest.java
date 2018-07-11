//package com.codecool.poop.dao;
//
//import com.codecool.poop.db_initializer.DummyDBInitializer;
//import com.codecool.poop.model.Skills;
//import com.codecool.poop.model.assignments.quiz.QuizAnswer;
//import com.codecool.poop.model.assignments.quiz.QuizAssignment;
//import com.codecool.poop.model.assignments.quiz.QuizQuestion;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class QuizQuestManagerTest {
//
//    private static QuizQuestManager qam;
//
//    @BeforeAll
//    private static void setupTest(){
//        UserManager userManager = new UserManager();
//        CodingQuestManager codingQuestManager = new CodingQuestManager();
//        QuizQuestManager quizQuestManager = new QuizQuestManager();
//        MasteryQuestManager masteryQuestManager = new MasteryQuestManager();
//
//        DummyDBInitializer dummyDBInitializer = new DummyDBInitializer(
//                userManager,
//                quizQuestManager,
//                codingQuestManager,
//                masteryQuestManager);
//        dummyDBInitializer.initializeDummyDB();
//
//        qam = quizQuestManager;
//    }
//
//    @Test
//    public void Should_ThrowException_When_TryToAddNull() {
//        assertAll(
//                () -> assertThrows(IllegalArgumentException.class, () -> qam.addQuizQuestionToDB(null)),
//                () -> assertThrows(IllegalArgumentException.class, () -> qam.addQuizAnswerToDB(null)),
//                () -> assertThrows(IllegalArgumentException.class, () -> qam.addQuizAssignmentToDB(null))
//        );
//    }
//
//    @Test
//    public void Should_AbleToFindQuestion_When_AddingValidQuestion() {
//        int id = 2;
//        assertEquals("Question 1 for assignment?", qam.getQuizQuestionByID(id).getQuestionText());
//    }
//
//    @Test
//    public void Should_AbleToFindAnswer_When_AddingValidAnswersToExistingQuestions() {
//        int id1 = 1;
//        int id2 = 2;
//        assertAll(
//                () -> assertEquals("Dog", qam.getQuizAnswerByID(id1).getAnswerText()),
//                () -> assertEquals("Cat", qam.getQuizAnswerByID(id2).getAnswerText())
//                );
//    }
//
//    @Test
//    public void Should_AbleToFindAssignment_When_AddingAssignmentWithOneQuestion() {
//        int id = 3;
//        assertEquals("My first assignment", qam.getQuizAssignemntByID(id).getName());
//    }
//}