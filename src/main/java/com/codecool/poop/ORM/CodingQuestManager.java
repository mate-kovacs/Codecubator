package com.codecool.poop.ORM;

import com.codecool.poop.model.Skills;
import com.codecool.poop.model.assignments.coding.CodingAnswer;
import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.model.assignments.coding.CodingQuestion;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Map;

public class CodingQuestManager extends DataManager{
    private static CodingQuestManager managerInstance = new CodingQuestManager();

    public static CodingQuestManager getInstance() {
        return managerInstance;
    }

    private CodingQuestManager() {
    }

    public void addCodingAssignmentToDB(CodingAssignment assignment){
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(assignment);
        transaction.commit();
    }

    public void addCodingQuestionToDB(CodingQuestion question){
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(question);
        transaction.commit();
    }

    public void addCodingAnswerToDB(CodingAnswer answer){
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(answer);
        transaction.commit();
    }

    public List<CodingAssignment> getAllCodingAssignments(){
        EntityManager entityManager = getEntityManager();
        List<CodingAssignment> assignments = entityManager.createQuery("SELECT assignment " +
                "FROM CodingAssignment as assignment").getResultList();
        return assignments;
    }

    public CodingAssignment getCodingAssignemntByID(Integer id){
        EntityManager entityManager = getEntityManager();
        CodingAssignment temp = entityManager.find(CodingAssignment.class, id);
        return temp;
    }

    public CodingQuestion getCodingQuestionByID(Integer id){
        EntityManager entityManager = getEntityManager();
        return entityManager.find(CodingQuestion.class, id);
    }

    public CodingAnswer getCodingAnswerByID(Integer id){
        EntityManager entityManager = getEntityManager();
        return entityManager.find(CodingAnswer.class, id);
    }
}
