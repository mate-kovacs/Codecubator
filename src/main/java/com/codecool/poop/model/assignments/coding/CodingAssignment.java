package com.codecool.poop.model.assignments.coding;

import com.codecool.poop.model.assignments.Assignment;
import com.codecool.poop.model.Skills;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "coding_assignments")
public class CodingAssignment extends Assignment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int assignmentID;

    @OneToMany(mappedBy = "assignment")
    private List<CodingQuestion> questions;

    public CodingAssignment(String name, String description, Map<Skills, Integer> expRewards, Integer codeCoinReward) {
        super(name, description, expRewards, codeCoinReward);
    }

    public CodingAssignment(){
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

    public int getAssignmentID() {
        return assignmentID;
    }
}
