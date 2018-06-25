package com.codecool.poop.dao;

import com.codecool.poop.model.Skills;
import com.codecool.poop.model.User;
import com.codecool.poop.model.assignments.Assignment;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Map;

@Component
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

    /**
     *
     * @param user a User
     * @param assignment an Assignment
     */
    public static void addRewardToUser(User user, Assignment assignment) {
        int codeCoinReward = assignment.getCodeCoinReward();
        int userCoins = user.getCodeCoins() + codeCoinReward;
        user.setCodeCoins(userCoins);
        Map<Skills, Integer> expRewards = assignment.getExpRewards();
        for (Map.Entry<Skills, Integer> entry : expRewards.entrySet()) {
            Skills skill = entry.getKey();
            Integer value = entry.getValue();
            user.addXpValueToSkill(skill, value);
        }
    }
}
