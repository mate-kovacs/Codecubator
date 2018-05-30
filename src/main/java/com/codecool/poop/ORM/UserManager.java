package com.codecool.poop.ORM;

import com.codecool.poop.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserManager extends DataManager {

    public void addUser(User user) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(user);
        transaction.commit();
        em.close();
    }

    public User getUser(int id) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        User user = em.find(User.class, id);
        transaction.commit();
        em.close();
        return user;
    }
}
