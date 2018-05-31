package com.codecool.poop.model.assignments.coding;

import com.codecool.poop.model.assignments.Assignment;
import com.codecool.poop.model.Skills;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "coding_assignments")
public class CodingAssignment extends Assignment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assignmentID;

    @OneToOne(mappedBy = "assignment")
    private CodingQuestion question;

    public CodingAssignment(String name, String description, Map<Skills, Integer> expRewards, Integer codeCoinReward) {
        super(name, description, expRewards, codeCoinReward);
    }

    public CodingAssignment(){
    }

    public void evaluateAnswer(CodingAnswer answer){
        int numberOfCorrectSolutions = question.correctSolutions(answer);
        //TODO
    }

    public int getMaxPoints(){
        return question.getMaxPoints();
    }

    public CodingQuestion getQuestion(){
        return question;
    }

    public void setQuestion(CodingQuestion question) {
        this.question = question;
    }

    public int getAssignmentID() {
        return assignmentID;
    }
}
