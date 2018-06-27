package com.codecool.poop.model.assignments.coding;

import com.codecool.poop.model.Rooms;
import com.codecool.poop.model.assignments.Assignment;
import com.codecool.poop.model.Skills;

import javax.persistence.*;
import java.util.*;

@Entity
@DiscriminatorValue("CODING")
public class CodingAssignment extends Assignment{


    @ManyToMany(mappedBy = "assignments")
    private List<CodingQuestion> questions = new ArrayList<>();

    public CodingAssignment(String name,
                            String description,
                            Map<Skills, Integer> expRewards,
                            Integer codeCoinReward,
                            List<CodingQuestion> questions,
                            Rooms room) {
        super(name, description, expRewards, codeCoinReward, room);
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

    public List<CodingQuestion> getQuestions(){
        return questions;
    }

    public void setQuestions(List<CodingQuestion> questions) {
        this.questions = questions;
    }
}
