package com.codecool.poop.service;

import com.codecool.poop.model.Skills;
import com.codecool.poop.model.User;
import com.codecool.poop.model.assignments.Assignment;
import com.codecool.poop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean saveUser(User entity) {
        try {
            userRepository.save(entity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public User getUserByName(String name) throws NoResultException {
        return userRepository.findUserByUsername(name);
    }

    public void addRewardToUser(User user, Assignment assignment) {
        addCoinRewardToUser(user, assignment);
        addSkillRewardToUser(user, assignment);
    }

    private void addCoinRewardToUser(User user, Assignment assignment) {
        int codeCoinReward = assignment.getCodeCoinReward();
        int userCoins = user.getCodeCoins() + codeCoinReward;
        user.setCodeCoins(userCoins);
    }

    private void addSkillRewardToUser(User user, Assignment assignment) {
        Map<Skills, Integer> expRewards = assignment.getExpRewards();
        for (Map.Entry<Skills, Integer> entry : expRewards.entrySet()) {
            Skills skill = entry.getKey();
            Integer value = entry.getValue();
            user.addXpValueToSkill(skill, value);
        }
    }


}
