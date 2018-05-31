package com.codecool.poop.model.assignments.quiz;

import com.codecool.poop.model.Skills;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuizAssignmentTest {

    private static List<QuizQuestion> questions = new ArrayList<>();

    @BeforeAll
    private static void createQuestion() {
        QuizQuestion question01 = new QuizQuestion("Question 01");

        new QuizAnswer("Dog", false, question01);
        new QuizAnswer("Cat", true, question01);
        new QuizAnswer("Tiger", true, question01);
        new QuizAnswer("Bear", false, question01);

        questions.add(question01);

        QuizQuestion question02 = new QuizQuestion("Question 02");

        new QuizAnswer("Dog", false, question02);
        new QuizAnswer("Cat", true, question02);
        new QuizAnswer("Tiger", false, question02);
        new QuizAnswer("Bear", false, question02);

        questions.add(question02);
    }

    @Test
    void Should_ReturnSumOfAllValidAnswers_When_GetValidQuestions() {
        Map<Skills, Integer> skillMap = new HashMap<>();
        skillMap.put(Skills.DATA_STRUCTURES, 10);
        skillMap.put(Skills.ALGORITHMS, 5);
        QuizAssignment assignment = new QuizAssignment("Test assignment", "This is an amazing assignment", skillMap, 10, questions);
        assertEquals(3, (int) assignment.getMaxPoint());
    }

    @Test
    void Should_ThrowIllegalArgumentEx_When_GivenNullExpMap() {
        Map<Skills, Integer> expMap = null;
        assertThrows(IllegalArgumentException.class, () -> new QuizAssignment("Test assignment", "This is an amazing assignment", expMap, 10, questions));
    }

    @Test
    void Should_ThrowIllegalArgumentEx_When_GivenInvalidExpValueInMap() {
        Map<Skills, Integer> expMap1 = new HashMap<>();
        expMap1.put(Skills.DATA_STRUCTURES, -10);
        expMap1.put(Skills.ALGORITHMS, 5);
        assertThrows(IllegalArgumentException.class, () -> new QuizAssignment("Test assignment", "This is an amazing assignment", expMap1, 10, questions));
    }

    @Test
    void Should_ThrowIllegalArgumentEx_When_GivenZeroLengthExpMap() {
        Map<Skills, Integer> expMap2 = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> new QuizAssignment("Test assignment", "This is an amazing assignment", expMap2, 10, questions));
    }

}