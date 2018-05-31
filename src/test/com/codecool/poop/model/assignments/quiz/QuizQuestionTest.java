package com.codecool.poop.model.assignments.quiz;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class QuizQuestionTest {

    QuizQuestion question;


    @BeforeEach
    private void createQuestion() {
        question = new QuizQuestion("Which one is dog?");
    }

    @Test
    void Should_ThrowIllegalArgumentEx_When_LessThan2Answers() {
        new QuizAnswer("Dog", true, question);
        assertFalse(question.isQuestionValid());
    }

    @Test
    void Should_ThrowIllegalArgumentEx_When_NoTrueInAnswers() {
        new QuizAnswer("Cat", false, question);
        assertFalse(question.isQuestionValid());
    }

    @Test
    void Should_AbleToSetAnswers_When_ThereIsAtLeastOneTrueAnswer() {
        new QuizAnswer("Cat", false, question);
        new QuizAnswer("Dog", true, question);
        assertTrue(question.isQuestionValid());
    }

    @Test
    void Should_Return0_When_NoAnswers() {
        assertEquals(0, question.getMaxPoints());
    }

    @Test
    void Should_ReturnNumberOfTrueAnswers_When_ValidAnswers() {
        new QuizAnswer("Cat", false, question);
        new QuizAnswer("Dog", true, question);
        new QuizAnswer("Dog2", true, question);
        assertEquals(2, question.getMaxPoints());
    }
}