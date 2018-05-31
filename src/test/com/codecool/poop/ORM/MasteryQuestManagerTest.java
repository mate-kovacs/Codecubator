package com.codecool.poop.ORM;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MasteryQuestManagerTest {

    static MasteryQuestManager manager;

    @BeforeAll
    private static void setupTest(){
        manager = MasteryQuestManager.getInstance();
    }

    @Test
    public void Should_ThrowException_When_TryToAddNull() {
        assertThrows(IllegalArgumentException.class, () -> manager.addMasteryAssignmentToDB(null));
    }

}