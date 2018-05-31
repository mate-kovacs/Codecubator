package com.codecool.poop.ORM;

import com.codecool.poop.model.Skills;
import com.codecool.poop.model.User;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public User getUserByName(String name) {
        EntityManager em = getEntityManager();
        String sql = "SELECT u from  User u where username = :name";
        TypedQuery<User> query = em.createQuery(sql, User.class);
        query.setParameter("name", name);
        return query.getResultList().get(0);
    }
}
