package com.codecool.poop.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

abstract class DataManager {
    private static EntityManager em;

    public static EntityManager getEntityManager() {
        if (em == null) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("codecubatorPU");
            em = entityManagerFactory.createEntityManager();
        }
        return em;
    }
}
