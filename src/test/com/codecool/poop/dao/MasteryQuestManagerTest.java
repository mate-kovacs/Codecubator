package com.codecool.poop.dao;

import com.codecool.poop.model.Skills;
import com.codecool.poop.model.assignments.coding.CodingQuestion;
import com.codecool.poop.model.assignments.mastery.MasteryAssignment;
import com.codecool.poop.model.assignments.quiz.QuizQuestion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MasteryQuestManagerTest {

    static MasteryQuestManager manager;

    @BeforeAll
    private static void setupTest(){
        manager = new MasteryQuestManager();
    }

    @Test
    public void Should_ThrowException_When_TryToAddNull() {
        assertThrows(IllegalArgumentException.class, () -> manager.addMasteryAssignmentToDB(null));
    }

    @Test
    public void Should_AbleToFindAssignment_When_AddingValidAssignment() {
        Map<Skills, Integer> reward = new HashMap<>();
        reward.put(Skills.ALGORITHMS, 10);
        MasteryAssignment assignment = new MasteryAssignment("Name",
                "Description",
                reward,
                5,
                null,
                null);
        QuizQuestion question11 = new QuizQuestion("Quiz question");
        QuizQuestion question12 = new QuizQuestion("Quit question");
        CodingQuestion question21 = new CodingQuestion("Coding question");
        CodingQuestion question22 = new CodingQuestion("Modding question");
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        List<CodingQuestion> codingQuestions = new ArrayList<>();
        quizQuestions.add(question11);
        quizQuestions.add(question12);
        codingQuestions.add(question21);
        codingQuestions.add(question22);

        CodingQuestManager codingManager = new CodingQuestManager();
        QuizQuestManager quizManager = new QuizQuestManager();

        for (CodingQuestion question: codingQuestions) {
            codingManager.addCodingQuestionToDB(question);
        }
        for (QuizQuestion question: quizQuestions) {
            quizManager.addQuizQuestionToDB(question);
        }

        int id = manager.addMasteryAssignmentToDB(assignment);

        assertEquals(assignment.getName(), manager.getMasteryAssignemntByID(id).getName());

    }

}