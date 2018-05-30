package com.codecool.poop.ORM;

import com.codecool.poop.model.assignments.coding.CodingAnswer;
import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.model.assignments.coding.CodingQuestion;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CodingAssignmentManager extends DataManager {
    private static CodingAssignmentManager managerInstance = new CodingAssignmentManager();

    public static CodingAssignmentManager getInstance() {
        return managerInstance;
    }

    private CodingAssignmentManager() {
    }

    public void addCodingAssignmentToDB(CodingAssignment assignment,
                                    CodingQuestion question,
                                    CodingAnswer answer){
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(answer);
        entityManager.persist(question);
        entityManager.persist(assignment);

        transaction.commit();
        entityManager.close();
    }

    public CodingAssignment getCodingAssignemntByID(Integer id){
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        CodingAssignment assignment = entityManager.find(CodingAssignment.class, id);

        transaction.commit();
        entityManager.close();

        return assignment;
    }
}
