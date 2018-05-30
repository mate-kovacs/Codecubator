package com.codecool.poop.model.assignments.coding;

import javax.persistence.*;

@Entity
@Table(name = "coding_question")
public class CodingQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "question_text", nullable = false)
    private String question;

    @OneToOne(mappedBy = "question")
    private CodingAnswer answer;

    @OneToOne
    @JoinColumn(name = "assignment_id")
    private CodingAssignment assignment;

    public CodingQuestion(String question) {
        this.question = question;
    }

    public CodingQuestion(){
    }

    public Integer correctSolutions(CodingAnswer currentAnswer){
        return answer.matchingSolutions(currentAnswer);
    }

    public int getMaxPoints() {
        return answer.getAnswers().size();
    }

    public CodingAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(CodingAnswer answer) {
        this.answer = answer;
    }

    public String getQuestions() {
        return question;
    }

    public void setAssignment(CodingAssignment assignment) {
        this.assignment = assignment;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getQuestions();
    }
}
