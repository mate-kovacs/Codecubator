package com.codecool.poop.ORM;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

abstract class DataManager {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("codecubatorPU");

    static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
