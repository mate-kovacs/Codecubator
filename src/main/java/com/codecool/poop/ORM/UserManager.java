package com.codecool.poop.ORM;

import com.codecool.poop.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UserManager extends DataManager {

    public boolean registerUser(User user) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(user);
            transaction.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public User getUserByName(String name) throws NoResultException{
        EntityManager em = getEntityManager();
        String sql = "SELECT u from  User u where username = :name";
        TypedQuery<User> query = em.createQuery(sql, User.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
