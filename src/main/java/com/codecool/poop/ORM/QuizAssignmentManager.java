package com.codecool.poop.ORM;

import com.codecool.poop.model.assignments.quiz.QuizAnswer;
import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import com.codecool.poop.model.assignments.quiz.QuizQuestion;

import java.util.List;

public class QuizAssignmentManager extends DataManager{
    private static QuizAssignmentManager ourInstance = new QuizAssignmentManager();

    public static QuizAssignmentManager getInstance() {
        return ourInstance;
    }

    private QuizAssignmentManager() {
    }

    public void addQuizAssignmentToDB(QuizAssignment assignment){
//        if (!(assignment instanceof QuizAssignment)) {
//            throw (new NullPointerException("That's a null not a QuizAssigment"));
//        }
//        List<QuizQuestion> questionList = assignment.getQuestions();
//        List<QuizAnswer> answerList;
//        for (QuizQuestion question : questionList) {
//            answerList.add(question.)
//        }
//        getEntityManager().persist(assignment);
    }

    public void addQuizQuestionToDB(QuizQuestion question) {
        getEntityManager().persist(question);

    }

    public void addQuizAnswerToDB(QuizAnswer answer, int questionId) {
        getEntityManager().persist(answer);

    }

    public QuizAssignment getQuizAssignemntByID(Integer id){
        return getEntityManager().find(QuizAssignment.class, id);
    }

    public QuizQuestion getQuizQuestionByID(Integer id){
        return getEntityManager().find(QuizQuestion.class, id);
    }

    public QuizAnswer getQuizAnswerByID(Integer id){
        return getEntityManager().find(QuizAnswer.class, id);
    }
}