package com.codecool.poop.model.assignments.quiz;

import com.codecool.poop.model.Skills;
import com.codecool.poop.model.assignments.Assignment;

import java.util.List;
import java.util.Map;

public class QuizAssignment extends Assignment {
    private List<QuizQuestion> questions;

    public QuizAssignment(String name, String description, Map<Skills, Integer> expRewards, Integer codeCoinReward, List<QuizQuestion> questions) {
        super(name, description, expRewards, codeCoinReward);
        this.questions = questions;
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizQuestion> questions) {
        this.questions = questions;
    }

    public Integer getMaxPoint() {
        return questions.stream()
                .mapToInt(QuizQuestion::getMaxPoints)
                .sum();
    }
}
