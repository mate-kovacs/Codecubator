package com.codecool.poop.ORM;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DataHandler {
    private static DataHandler ourInstance = new DataHandler();

    public static DataHandler getInstance() {
        return ourInstance;
    }

    private DataHandler() {
    }

    public void addItemToDB(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("codecubatorPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(object);
        transaction.commit();

        em.close();
        emf.close();
    }
}
