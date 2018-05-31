package com.codecool.poop.ORM;

import com.codecool.poop.model.Skills;
import com.codecool.poop.model.User;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserManager extends DataManager {

    public boolean registerUser(User user) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(user);
        } catch (ConstraintViolationException e) {
            return false;
        }
        transaction.commit();
        return true;
    }

    public User getUserByName(String name) {
        EntityManager em = getEntityManager();
        String sql = "SELECT u from  User u where username = :name";
        TypedQuery<User> query = em.createQuery(sql, User.class);
        query.setParameter("name", name);
        return query.getResultList().get(0);
    }


    public static void main(String[] args) {
        User user = new User("valaki", "pass", "email");
        UserManager userManager = new UserManager();
        //userManager.addUser(user);
    }
}
