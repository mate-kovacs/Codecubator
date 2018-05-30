package com.codecool.poop.ORM;

public class CodingAssignmentManager extends DataManager {
    private static CodingAssignmentManager managerInstance = new CodingAssignmentManager();

    public static CodingAssignmentManager getInstance() {
        return managerInstance;
    }

    private CodingAssignmentManager() {
    }
}
