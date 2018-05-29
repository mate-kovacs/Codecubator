package com.codecool.poop.model.assignments.quiz;

public class QuizAnswer {
    private String answerText;
    private Boolean answerValidity;

    public QuizAnswer(String answerText, Boolean answerValidity) {
        this.answerText = answerText;
        this.answerValidity = answerValidity;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Boolean getAnswerValidity() {
        return answerValidity;
    }

    public void setAnswerValidity(Boolean answerValidity) {
        this.answerValidity = answerValidity;
    }
}
