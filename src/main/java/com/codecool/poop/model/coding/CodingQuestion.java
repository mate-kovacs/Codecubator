package com.codecool.poop.model.coding;

import java.util.List;

public class CodingQuestion {

    private String instruction;
    private List<String> questions;
    private CodingAnswer answer;

    public CodingQuestion(String instruction, List<String> questions) {
        this.instruction = instruction;
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Your task: " + instruction +
                "\n" + questions;
    }

    public boolean isAnswerCorrect(List<String> answer) {
        return this.answer.isMatching(answer);
    }

    public void setAnswer(CodingAnswer answer) {
        this.answer = answer;
    }
}
