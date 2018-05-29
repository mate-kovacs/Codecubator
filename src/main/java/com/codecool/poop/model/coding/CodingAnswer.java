package com.codecool.poop.model.coding;

import java.util.List;

public class CodingAnswer {

    private List<String> goodAnswers;

    public CodingAnswer(List<String> goodAnswers) {
        this.goodAnswers = goodAnswers;
    }

    public boolean isMatching(List<String> answer) {
        boolean result = true;
        for (String solution: answer) {
            if (!solution.equals(goodAnswers.get(answer.indexOf(solution)))){
                result = false;
            }
        }
        return result;
    }
}
