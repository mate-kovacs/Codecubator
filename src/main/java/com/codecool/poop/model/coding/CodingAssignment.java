package com.codecool.poop.model.coding;

import com.codecool.poop.model.Assignment;
import com.codecool.poop.model.Skills;
import com.codecool.poop.model.User;

import java.util.List;
import java.util.Map;

public class CodingAssignment extends Assignment{

    private CodingQuestion question;

    public CodingAssignment(String name, String description, Map<Skills, Integer> expRewards, Integer codeCoinReward) {
        super(name, description, expRewards, codeCoinReward);
    }

    public String getQuestion(){
        return question.toString();
    }

    public boolean evaluateSolution(List<String> answer){
        return question.isAnswerCorrect(answer);
    }

    public void setQuestion(CodingQuestion question) {
        this.question = question;
    }
}
