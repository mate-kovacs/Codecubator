package com.codecool.poop.model.assignments.quiz;

import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuizAssignmentTest {

    @BeforeAll
    private static void createQuestion() {
        List<QuizAnswer> answers1 = new ArrayList<>();
        answers1.add(new QuizAnswer("Dog", false));
        answers1.add(new QuizAnswer("Cat", true));
        answers1.add(new QuizAnswer("Tiger", true));
        answers1.add(new QuizAnswer("Bear", false));

        List<QuizAnswer> answers2 = new ArrayList<>();
        answers2.add(new QuizAnswer("Dog", false));
        answers2.add(new QuizAnswer("Cat", false));
        answers2.add(new QuizAnswer("Tiger", false));
        answers2.add(new QuizAnswer("Bear", false));
    }

}