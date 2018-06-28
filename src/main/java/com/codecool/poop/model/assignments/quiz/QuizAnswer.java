package com.codecool.poop.model.assignments.quiz;

import javax.persistence.*;

@Entity
@Table(name = "quiz_answers")
public class QuizAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_answer_id")
    private int id;
    private String answerText;
    private Boolean answerValidity;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuizQuestion question;

    public QuizAnswer(String answerText, Boolean answerValidity, QuizQuestion question) {
        this.answerText = answerText;
        this.answerValidity = answerValidity;
        this.question = question;
        question.addAnswer(this);
    }

    public QuizAnswer() {}

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

    public QuizQuestion getQuestion() {
        return question;
    }

    public void setQuestion(QuizQuestion question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
