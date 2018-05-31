package com.codecool.poop.ORM;

import com.codecool.poop.model.assignments.mastery.MasteryAssignment;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MasteryQuestManager extends DataManager{

    private static MasteryQuestManager managerInstance = new MasteryQuestManager();

    public static MasteryQuestManager getInstance() {
        return managerInstance;
    }

    private MasteryQuestManager(){
    }

    public int addMasteryAssignmentToDB(MasteryAssignment assignment){
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(assignment);
        transaction.commit();
        return assignment.getId();
    }

    public MasteryAssignment getMasteryAssignemntByID(Integer id){
        EntityManager entityManager = getEntityManager();
        return entityManager.find(MasteryAssignment.class, id);
    }
}
