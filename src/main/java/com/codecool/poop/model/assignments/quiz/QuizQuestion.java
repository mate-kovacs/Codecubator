package com.codecool.poop.model.assignments.quiz;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz_questions")
public class QuizQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String questionText;
    @OneToMany(mappedBy = "question")
    private List<QuizAnswer> quizAnswers = new ArrayList<>();

    protected QuizQuestion() {}

    public QuizQuestion(String questionText) {
        this.questionText = questionText;
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
        if (isAnswersValid(quizAnswers)) {
            this.quizAnswers = quizAnswers;
            for (QuizAnswer answer : quizAnswers) {
                answer.setQuestion(this);
            }
        } else {
            throw new IllegalArgumentException("Invalid answers");
        }
    }

    public int getMaxPoints() {
        return (int) quizAnswers.stream()
                .filter(QuizAnswer::getAnswerValidity)
                .count();
    }


}
