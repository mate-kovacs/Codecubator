package com.codecool.poop.model.assignments.quiz;

import com.codecool.poop.model.Skills;
import com.codecool.poop.model.assignments.Assignment;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "quiz_assignment")
public class QuizAssignment extends Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assigmentId;
    @ManyToMany
    private List<QuizQuestion> questions;

    protected QuizAssignment() {}

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
