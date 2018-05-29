package com.codecool.poop.model.assignments.quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestion {

    private String questionText;
    private List<QuizAnswer> quizAnswers = new ArrayList<>();

    public QuizQuestion(String questionText, List<QuizAnswer> quizAnswers) {
        this.questionText = questionText;
        if (isAnswersValid(quizAnswers)) {
            this.quizAnswers = quizAnswers;
        } else {
            throw new IllegalArgumentException("Invalid answers");
        }
    }

    private Boolean isAnswersValid(List<QuizAnswer> answers) {
        if (answers == null || answers.size() < 2) return false;
        for (QuizAnswer answer : answers) {
            if (answer.getAnswerValidity()) return true;
        }
        return false;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<QuizAnswer> getQuizAnswers() {
        return quizAnswers;
    }

    public void setQuizAnswers(List<QuizAnswer> quizAnswers) {
        this.quizAnswers = quizAnswers;
    }

    public int getMaxPoints() {
        return (int) quizAnswers.stream()
                .filter(QuizAnswer::getAnswerValidity)
                .count();
    }
}
