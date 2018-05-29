package com.codecool.poop.model.coding;

import com.codecool.poop.model.Assignment;
import com.codecool.poop.model.Skills;

import java.util.List;
import java.util.Map;

public class CodingAssignment extends Assignment{

    private CodingQuestion question;

    public CodingAssignment(String name, String description, Map<Skills, Integer> expRewards, Integer codeCoinReward) {
        super(name, description, expRewards, codeCoinReward);
    }

    public void evaluateAnswer(CodingAnswer answer){
        int numberOfCorrectSolutions = question.correctSolutions(answer);
        //TODO
    }

    public int getMaxPoints(){
        return question.getMaxPoints();
    }

    public CodingQuestion getQuestion(){
        return question;
    }

    public void setQuestion(CodingQuestion question) {
        this.question = question;
    }
}
