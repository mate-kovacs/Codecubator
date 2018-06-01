package com.codecool.poop.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

abstract class DataManager {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("codecubatorPU");
    private static EntityManager em = entityManagerFactory.createEntityManager();

    public static EntityManager getEntityManager() {
        return em;
    }
}
