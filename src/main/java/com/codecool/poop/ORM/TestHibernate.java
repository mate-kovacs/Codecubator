package com.codecool.poop.ORM;

import com.codecool.poop.model.Skills;
//import com.codecool.poop.model.User;
import com.codecool.poop.model.assignments.quiz.QuizAnswer;
import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import com.codecool.poop.model.assignments.quiz.QuizQuestion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHibernate {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("codecubatorPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();


        // Example for test the database
        List<QuizQuestion> questions = new ArrayList<>();

        List<QuizAnswer> answers1 = new ArrayList<>();
        answers1.add(new QuizAnswer("Dog", false));
        answers1.add(new QuizAnswer("Cat", true));
        answers1.add(new QuizAnswer("Tiger", true));
        answers1.add(new QuizAnswer("Bear", false));
        QuizQuestion question01 = new QuizQuestion("Question 01");
        question01.setQuizAnswers(answers1);
        questions.add(question01);


        List<QuizAnswer> answers2 = new ArrayList<>();
        answers2.add(new QuizAnswer("Dog", false));
        answers2.add(new QuizAnswer("Cat", true));
        answers2.add(new QuizAnswer("Tiger", false));
        answers2.add(new QuizAnswer("Bear", false));
        QuizQuestion question02 = new QuizQuestion("Question 02");
        question02.setQuizAnswers(answers2);
        questions.add(question02);

        Map<Skills, Integer> skillMap = new HashMap<>();
        skillMap.put(Skills.DATA_STRUCTURES, 10);
        skillMap.put(Skills.ALGORITHMS, 5);

        QuizAssignment assignment = new QuizAssignment("Test assignment", "This is an amazing assignment", skillMap, 10, questions);

        transaction.begin();

        for (QuizAnswer answer : answers1) {
            em.persist(answer);
        }

        for (QuizAnswer answer : answers2) {
            em.persist(answer);
        }

        for (QuizQuestion question : questions) {
            em.persist(question);
        }

        em.persist(assignment);

        transaction.commit();

        em.close();
        emf.close();

    }
}
