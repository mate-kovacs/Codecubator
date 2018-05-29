package com.codecool.poop.model.coding;

import java.util.ArrayList;
import java.util.List;

public class CodingAnswer {

    private List<String> answers;

    public CodingAnswer() {
    }

    private List<String> validateAnswers(List<String> answers){
        List<String> goodAnswers = new ArrayList<>();
        for (String answer : answers){
            String[] answerParts = answer.split("[\\s]+");
            String temp = "";
            for (String answerPart : answerParts) {
                temp = temp.concat(answerPart);
            }
            goodAnswers.add(temp);
        }
        return goodAnswers;
    }

    public Integer matchingSolutions(CodingAnswer answer){
        Integer numberOfMatchingSolutions = 0;
        for (String solution : answer.getAnswers()){
            if (isMatching(answer.getAnswers().indexOf(solution), solution)){
                numberOfMatchingSolutions ++;
            }
        }
        return numberOfMatchingSolutions;
    }

    private boolean isMatching(int index, String solution){
        return  solution.equals(this.answers.get(index));
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = validateAnswers(answers);
    }

}
