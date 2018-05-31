package com.codecool.poop.ORM;

import com.codecool.poop.model.assignments.quiz.QuizAnswer;
import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import com.codecool.poop.model.assignments.quiz.QuizQuestion;

public class QuizQuestManager extends DataManager {
    private static QuizQuestManager ourInstance = new QuizQuestManager();

    public static QuizQuestManager getInstance() {
        return ourInstance;
    }

    private QuizQuestManager() {
    }

    public int addQuizAssignmentToDB(QuizAssignment assignment) {
        if(!(assignment instanceof QuizAssignment)){
            throw new IllegalArgumentException("It's not a QuizAssignment");
        }
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(assignment);
        getEntityManager().getTransaction().commit();
        return assignment.getId();
    }

    public int addQuizQuestionToDB(QuizQuestion question) {
        if(!(question instanceof QuizQuestion)){
            throw new IllegalArgumentException("It's not a QuizQuestion");
        }
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(question);
        getEntityManager().getTransaction().commit();
        return question.getId();

    }

    public int addQuizAnswerToDB(QuizAnswer answer) {
        if(!(answer instanceof QuizAnswer)){
            throw new IllegalArgumentException("It's not a QuizAnswer");
        }
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(answer);
        getEntityManager().getTransaction().commit();
        return answer.getId();
    }

    public QuizAssignment getQuizAssignemntByID(Integer id) {
        return getEntityManager().find(QuizAssignment.class, id);
    }

    public QuizQuestion getQuizQuestionByID(Integer id) {
        return getEntityManager().find(QuizQuestion.class, id);
    }

    public QuizAnswer getQuizAnswerByID(Integer id) {
        return getEntityManager().find(QuizAnswer.class, id);
    }
}