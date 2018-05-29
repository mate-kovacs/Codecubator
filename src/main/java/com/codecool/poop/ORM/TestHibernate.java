package com.codecool.poop.ORM;


import com.codecool.poop.model.TestUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestHibernate {

    public static void main(String[] args) {
        TestUser testUser = new TestUser("valaki");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(testUser);

        transaction.commit();
        em.close();
        emf.close();
    }
}
