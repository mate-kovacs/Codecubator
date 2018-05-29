package com.codecool.poop.model.assignments.quiz;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class QuizQuestionTest {

    static List<QuizAnswer> validAnswers = new ArrayList<>();
    static List<QuizAnswer> allFalseAnswers = new ArrayList<>();
    static List<QuizAnswer> nullAnswers = null;
    static List<QuizAnswer> notEnoughAnswers = new ArrayList<>();

    @BeforeAll
    private static void createQuestion() {
        validAnswers.add(new QuizAnswer("Dog", false));
        validAnswers.add(new QuizAnswer("Cat", true));
        validAnswers.add(new QuizAnswer("Tiger", true));
        validAnswers.add(new QuizAnswer("Bear", false));

        allFalseAnswers.add(new QuizAnswer("Dog", false));
        allFalseAnswers.add(new QuizAnswer("Cat", false));
        allFalseAnswers.add(new QuizAnswer("Tiger", false));
        allFalseAnswers.add(new QuizAnswer("Bear", false));

        notEnoughAnswers.add(new QuizAnswer("Dog", false));
    }

    @Test
    void Should_ThrowIllegalArgumentEx_When_LessThan2Answers() {
        assertThrows(IllegalArgumentException.class, () -> new QuizQuestion("Which one is dog", allFalseAnswers));
    }

    @Test
    void Should_ThrowIllegalArgumentEx_When_NoTrueInAnswers() {
        assertThrows(IllegalArgumentException.class, () -> new QuizQuestion("Which one is/are plant?", allFalseAnswers));
    }

    @Test
    void Should_CreateQuizQuestionObject_When_ThereIsAtLeastOneTrueAnswer() {
        QuizQuestion question = new QuizQuestion("Which one is/are plant?", validAnswers);
    }

    @Test
    void Should_ReturnNumberOfTrueAnswers_When_ValidAnswers() {
        QuizQuestion question = new QuizQuestion("Which one is/are plant?", validAnswers);
        assertEquals(2, question.getMaxPoints());
    }
}