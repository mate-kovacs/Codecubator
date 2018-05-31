package com.codecool.poop.ORM;

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

    public void addCodingAssignmentWithQuestionsAndAnswersToDB(CodingAssignment assignment){
        for (CodingQuestion question: assignment.getQuestions()) {
            addCodingQuestionWithAnswersToDB(question);
        }
        addCodingAssignmentToDB(assignment);
    }

    public void addCodingQuestionWithAnswersToDB(CodingQuestion question){
        for (CodingAnswer answer: question.getAnswers()){
            addCodingAnswerToDB(answer);
        }
        addCodingQuestionToDB(question);
    }

    public void addCodingAssignmentToDB(CodingAssignment assignment){
        EntityManager entityManager = getEntityManager();
        entityManager.persist(assignment);
    }

    public void addCodingQuestionToDB(CodingQuestion question){
        EntityManager entityManager = getEntityManager();
        entityManager.persist(question);
    }

    public void addCodingAnswerToDB(CodingAnswer answer){
        EntityManager entityManager = getEntityManager();
        entityManager.persist(answer);
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
