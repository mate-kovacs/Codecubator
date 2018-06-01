package com.codecool.poop.dao;

import com.codecool.poop.model.Skills;
import com.codecool.poop.model.assignments.quiz.QuizAnswer;
import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import com.codecool.poop.model.assignments.quiz.QuizQuestion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class QuizQuestManagerTest {

    static QuizQuestManager qam;

    @BeforeAll
    private static void setupTest(){
        qam = QuizQuestManager.getInstance();
    }

    @Test
    public void Should_ThrowException_When_TryToAddNull() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> qam.addQuizQuestionToDB(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> qam.addQuizAnswerToDB(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> qam.addQuizAssignmentToDB(null))
        );
    }

    @Test
    public void Should_AbleToFindQuestion_When_AddingValidQuestion() {
        QuizQuestion question = new QuizQuestion("Is this a lonely question?");
        int id = qam.addQuizQuestionToDB(question);
        assertEquals(question, qam.getQuizQuestionByID(id));
    }

    @Test
    public void Should_AbleToFindAnswer_When_AddingValidAnswersToExistingQuestions() {
        QuizQuestion question = new QuizQuestion("Which one is cat?");
        qam.addQuizQuestionToDB(question);
        QuizAnswer answer01 = new QuizAnswer("Dog", false, question);
        int id1 = qam.addQuizAnswerToDB(answer01);
        QuizAnswer answer02 = new QuizAnswer("Cat", true, question);
        int id2 = qam.addQuizAnswerToDB(answer02);
        assertAll(
                () -> assertEquals(answer01, qam.getQuizAnswerByID(id1)),
                () -> assertEquals(answer02, qam.getQuizAnswerByID(id2))
                );
    }

    @Test
    public void Should_AbleToFindAssignment_When_AddingAssignmentWithOneQuestion() {
        QuizQuestion question01 = new QuizQuestion("Question 1 for assignment?");
        qam.addQuizQuestionToDB(question01);
        QuizQuestion question02 = new QuizQuestion("Question 2 for assignment?");
        qam.addQuizQuestionToDB(question02);
        List<QuizQuestion> questions = new ArrayList<>(Arrays.asList(question01, question02));
        Map<Skills, Integer> rewardMap = new HashMap<>();
        rewardMap.put(Skills.ALGORITHMS, 10);
        rewardMap.put(Skills.HTML_BASIC, 20);
        QuizAssignment quizAssignment = new QuizAssignment(
                "My first assignment",
                "Very cool assigment for everyone",
                rewardMap,
                10,
                questions
                );
        int id = qam.addQuizAssignmentToDB(quizAssignment);
        assertEquals(quizAssignment, qam.getQuizAssignemntByID(id));
    }
}