package com.codecool.poop.model.assignments;

import com.codecool.poop.model.Skills;

import java.time.LocalDateTime;
import java.util.Map;

public abstract class Assignment {
    private String name;
    private String description;
    private Map<Skills,Integer> expRewards;
    private Integer codeCoinReward;
    private LocalDateTime creationDate;

    public Assignment(String name, String description, Map<Skills, Integer> expRewards, Integer codeCoinReward) {
        if (!isValidexpRewards(expRewards)) {
            throw new IllegalArgumentException("Invalid reward map");
        }
        this.name = name;
        this.description = description;
        this.expRewards = expRewards;
        this.codeCoinReward = codeCoinReward;
        this.creationDate = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Skills, Integer> getExpRewards() {
        return expRewards;
    }

    public void setExpRewards(Map<Skills, Integer> expRewards) {
        this.expRewards = expRewards;
    }

    public Integer getCodeCoinReward() {
        return codeCoinReward;
    }

    public void setCodeCoinReward(Integer codeCoinReward) {
        this.codeCoinReward = codeCoinReward;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    private boolean isValidexpRewards(Map<Skills, Integer> expRewards) {
        return expRewards != null && expRewards.size() > 0 && isNoNegativeInRewardsMap(expRewards);
    }

    private boolean isNoNegativeInRewardsMap(Map<Skills, Integer> expRewards) {
        for (Integer value : expRewards.values()) {
            if (value < 0) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", expRewards=" + expRewards +
                ", codeCoinReward=" + codeCoinReward +
                ", creationDate=" + creationDate +
                '}';
    }

}
