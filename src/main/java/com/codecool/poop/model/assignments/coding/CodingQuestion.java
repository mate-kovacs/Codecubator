package com.codecool.poop.model.assignments.coding;

import javax.persistence.*;
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
    private List<CodingAnswer> answers;

    @ManyToMany
    @JoinColumn(name = "assignment_id")
    private Set<CodingAssignment> assignments = new HashSet<>();

    public CodingQuestion(String question) {
        this.question = question;
    }

    public CodingQuestion(){
    }

    public Integer checkSolution(List<CodingAnswer> userAnswers){
        Integer numberOfCorrectAnswers = 0;
        for (CodingAnswer userAnswer: userAnswers) {
            CodingAnswer correctAnswer = answers.get(userAnswers.indexOf(userAnswer));
            if (correctAnswer.isMatching(userAnswer)){
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

    public void setAnswers(List<CodingAnswer> answers) {
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void addAssignment(CodingAssignment assignment) {
        this.assignments.add(assignment);
    }

    public void removeAssignment(CodingAssignment assignment){
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
