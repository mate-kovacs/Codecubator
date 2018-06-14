package com.codecool.poop.model.assignments.coding;

import com.codecool.poop.model.assignments.Assignment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "coding_question")
public class CodingQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "question_text", nullable = false)
    private String question;

    @OneToMany(mappedBy = "question")
    private List<CodingAnswer> answers = new ArrayList<>();

    @ManyToMany
    @JoinColumn(name = "assignment_id")
    private Set<Assignment> assignments = new HashSet<>();

    public CodingQuestion(String question) {
        this.question = question;
    }

    public CodingQuestion(){
    }

    public Integer checkSolution(List<String> userAnswers){
        int numberOfCorrectAnswers = 0;
        for (String userAnswer: userAnswers) {
            CodingAnswer correctAnswer = answers.get(userAnswers.indexOf(userAnswer));
            if (correctAnswer.isMatching(CodingAnswer.formatAnswer(userAnswer))){
                numberOfCorrectAnswers ++;
            }
        }
        return numberOfCorrectAnswers;
    }

    public int getMaxPoints() {
        return answers.size();
    }

    public List<CodingAnswer> getAnswers() {
        return answers;
    }

    public void addAnswer(CodingAnswer answer) {
        this.answers.add(answer);
    }

    public String getQuestion() {
        return question;
    }

    public void addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
    }

    public void removeAssignment(Assignment assignment){
        this.assignments.remove(assignment);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getQuestion();
    }
}
