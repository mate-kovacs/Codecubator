package com.codecool.poop.dao;

import com.codecool.poop.db_initializer.DummyDBInitializer;
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

    private static MasteryQuestManager masteryManager;

    @BeforeAll
    private static void setupTest(){
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

        masteryManager = masteryQuestManager;
    }

    @Test
    public void Should_ThrowException_When_TryToAddNull() {
        assertThrows(IllegalArgumentException.class, () -> masteryManager.addMasteryAssignmentToDB(null));
    }

    @Test
    public void Should_AbleToFindAssignment_When_AddingValidAssignment() {
        int id = 4;
        assertEquals("Name", masteryManager.getMasteryAssignemntByID(id).getName());

    }

}