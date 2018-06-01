package com.codecool.poop.dao;

import com.codecool.poop.model.assignments.coding.CodingAnswer;
import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.model.assignments.coding.CodingQuestion;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CodingQuestManager extends DataManager{
    private static CodingQuestManager managerInstance = new CodingQuestManager();

    public static CodingQuestManager getInstance() {
        return managerInstance;
    }

    private CodingQuestManager() {
    }

    public int addCodingAssignmentToDB(CodingAssignment assignment){
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(assignment);
        transaction.commit();
        return assignment.getId();
    }

    public int addCodingQuestionToDB(CodingQuestion question){
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(question);
        transaction.commit();
        return question.getId();
    }

    public int addCodingAnswerToDB(CodingAnswer answer){
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(answer);
        transaction.commit();
        return answer.getId();
    }

    public List<CodingAssignment> getAllCodingAssignments(){
        EntityManager entityManager = getEntityManager();
        List<CodingAssignment> assignments = entityManager.createQuery("SELECT assignment " +
                "FROM CodingAssignment as assignment").getResultList();
        return assignments;
    }

    public List<CodingQuestion> getAllCodingQuestions(){
        EntityManager entityManager = getEntityManager();
        List<CodingQuestion> questions = entityManager.createQuery("SELECT question " +
                "FROM CodingQuestion as question").getResultList();
        return questions;
    }

    public CodingAssignment getCodingAssignemntByID(Integer id){
        EntityManager entityManager = getEntityManager();
        return entityManager.find(CodingAssignment.class, id);
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
