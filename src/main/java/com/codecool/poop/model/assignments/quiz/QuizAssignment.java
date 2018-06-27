package com.codecool.poop.model.assignments.quiz;

import com.codecool.poop.model.Skills;
import com.codecool.poop.model.assignments.Assignment;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@DiscriminatorValue("QUIZ")
public class QuizAssignment extends Assignment {

    @ManyToMany (mappedBy = "assignments")
    private List<QuizQuestion> questions;

    protected QuizAssignment() {}

    @Override
    public Integer getMaxPoints() {
        return questions.stream()
                .mapToInt(QuizQuestion::getMaxPoints)
                .sum();
    }

    public QuizAssignment(String name, String description, Map<Skills, Integer> expRewards, Integer codeCoinReward, List<QuizQuestion> questions) {
        super(name, description, expRewards, codeCoinReward);
        this.questions = questions;
        setQuestionReferences();
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    private void setQuestionReferences(){
        for (QuizQuestion question : questions){
            question.addAssigment(this);
        }
    }

    public void setQuestions(List<QuizQuestion> questions) {
        this.questions = questions;
    }

}
