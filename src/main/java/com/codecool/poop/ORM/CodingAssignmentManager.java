package com.codecool.poop.ORM;

import com.codecool.poop.model.assignments.coding.CodingAnswer;
import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.model.assignments.coding.CodingQuestion;
import org.hibernate.SQLQuery;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.SQLData;
import java.util.List;

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

        CodingAssignment assignment = entityManager.find(CodingAssignment.class, id);

        entityManager.close();

        return assignment;
    }

    public CodingQuestion getCodingQuestionByID(Integer id){
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        CodingAssignment assignment = entityManager.find(CodingAssignment.class, id);

        entityManager.close();

        return assignment.getQuestion();
    }

    public CodingAnswer getCodingAnswerByID(Integer id){
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        CodingAssignment assignment = entityManager.find(CodingAssignment.class, id);
        CodingAnswer result = assignment.getQuestion().getAnswer();

       // entityManager.close();

        return result;
    }
}
