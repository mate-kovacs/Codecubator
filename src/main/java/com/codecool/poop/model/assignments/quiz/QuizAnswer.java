package com.codecool.poop.model.assignments.quiz;

import javax.persistence.*;

@Entity
@Table(name = "quiz_answers")
public class QuizAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int answerId;
    private String answerText;
    private Boolean answerValidity;

    public QuizAnswer(String answerText, Boolean answerValidity) {
        this.answerText = answerText;
        this.answerValidity = answerValidity;
    }

    protected QuizAnswer() {}

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
