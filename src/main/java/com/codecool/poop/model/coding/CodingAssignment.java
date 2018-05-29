package com.codecool.poop.model.coding;

import com.codecool.poop.model.Assignment;
import com.codecool.poop.model.Skills;

import java.util.Map;

public class CodingAssignment extends Assignment{

    public CodingAssignment(String name, String description, Map<Skills, Integer> expRewards, Integer codeCoinReward) {
        super(name, description, expRewards, codeCoinReward);
    }
}
