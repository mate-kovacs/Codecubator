package com.codecool.poop.dao;

import com.codecool.poop.model.assignments.mastery.MasteryAssignment;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class MasteryQuestManager extends DataManager{

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

    public List getAllAssignments(){
        EntityManager entityManager = getEntityManager();
        return entityManager.createQuery("SELECT assignment " +
                "FROM MasteryAssignment as assignment").getResultList();
    }

}
