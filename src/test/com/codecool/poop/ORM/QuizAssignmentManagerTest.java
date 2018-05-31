package com.codecool.poop.ORM;

import com.codecool.poop.model.Skills;
import com.codecool.poop.model.assignments.quiz.QuizAnswer;
import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import com.codecool.poop.model.assignments.quiz.QuizQuestion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class QuizAssignmentManagerTest {

    static QuizAssignmentManager qam;

    @BeforeAll
    private static void setupTest(){
        qam = QuizAssignmentManager.getInstance();
    }

    @Test
    public void Should_ThrowException_When_TryToAddNull() {
        assertThrows(NullPointerException.class, () -> qam.addQuizAssignmentToDB(null));
    }

    @Test
    public void Should_RunWithoutProblem_When_AddingValidQuizAssignment() {
        QuizAnswer answer01 = new QuizAnswer("Dog", false);
        QuizAnswer answer02 = new QuizAnswer("Cat", true);
        List<QuizAnswer> answers01 = new ArrayList<>(Arrays.asList(answer01, answer02));
        QuizQuestion question01 = new QuizQuestion("Whic one is cat?");
        question01.setQuizAnswers(answers01);

        List<QuizQuestion> questions01 = new ArrayList<>(Arrays.asList(question01));

        Map<Skills, Integer> rewardMap = new HashMap<>();
        rewardMap.put(Skills.ALGORITHMS, 10);
        rewardMap.put(Skills.HTML_BASIC, 20);

        QuizAssignment quizAssignment = new QuizAssignment(
                "My first assignment",
                "Very cool assigment for everyone",
                rewardMap,
                10,
                questions01
                );

        qam.addQuizAssignmentToDB(quizAssignment);
//        QuizAnswer answer03 = new QuizAnswer("Carrot", false);
//        QuizAnswer answer04 = new QuizAnswer("Potatoe", true);
//        List<QuizAnswer> answers02 = new ArrayList<>(Arrays.asList(answer01, answer02));
//        QuizQuestion question02 = new QuizQuestion("Whic one is potatoe?");
//        question01.setQuizAnswers(answers02);
    }

}