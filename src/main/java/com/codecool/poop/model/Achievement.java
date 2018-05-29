package com.codecool.poop.model;

public class Achievement {
    private String name;
    private String description;
    private int codeCoinReward;


    public Achievement(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Achievement(String name, String description, int codeCoinReward) {
        this.name = name;
        this.description = description;
        this.codeCoinReward = codeCoinReward;
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

    public int getCodeCoinReward() {
        return codeCoinReward;
    }

    public void setCodeCoinReward(int codeCoinReward) {
        this.codeCoinReward = codeCoinReward;
    }
}
