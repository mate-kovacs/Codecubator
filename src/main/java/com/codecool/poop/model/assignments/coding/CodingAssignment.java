package com.codecool.poop.model.assignments.coding;

import com.codecool.poop.model.assignments.Assignment;
import com.codecool.poop.model.Skills;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@DiscriminatorValue("CODING")
public class CodingAssignment extends Assignment{


    @ManyToMany(mappedBy = "assignments")
    private Set<CodingQuestion> questions = new HashSet<>();

    public CodingAssignment(String name,
                            String description,
                            Map<Skills, Integer> expRewards,
                            Integer codeCoinReward,
                            Set<CodingQuestion> questions) {
        super(name, description, expRewards, codeCoinReward);
        this.questions = questions;
        setCodingQuestionReferences();
    }

    public CodingAssignment(){
    }

    private void setCodingQuestionReferences(){
        for (CodingQuestion question: questions) {
            question.addAssignment(this);
        }
    }

    public int getMaxPoints(){
        int points = 0;
        for (CodingQuestion question: questions) {
            points += question.getMaxPoints();
        }
        return points;
    }

    public Set<CodingQuestion> getQuestions(){
        return questions;
    }

    public void setQuestions(Set<CodingQuestion> questions) {
        this.questions = questions;
    }
}
