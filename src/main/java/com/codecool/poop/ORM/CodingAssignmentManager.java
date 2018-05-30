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
        EntityManager em = getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(answer);
        em.persist(question);
        em.persist(assignment);
        transaction.commit();
        em.close();
    }
}
