package com.codecool.poop.model.assignments.coding;

import javax.persistence.*;

@Entity
@Table(name = "coding_answer")
public class CodingAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "answer_text")
    private String answer;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private CodingQuestion question;

    public static String formatAnswer(String answer) {
        String[] answerParts = answer.split("[\\s]+");
        String goodAnswer = "";
        for (String answerPart : answerParts) {
            goodAnswer = goodAnswer.concat(answerPart);
        }
        return goodAnswer;
    }

    public CodingAnswer() {
    }

    public CodingAnswer(String answer, CodingQuestion question) {
        this.answer = formatAnswer(answer);
        this.question = question;
        setCodingQuestionReference();
    }

    public void addQuestion(CodingQuestion question) {
        this.question = question;
        question.addAnswer(this);
    }

    private void setCodingQuestionReference(){
        question.addAnswer(this);
    }

    public boolean isMatching(String answerToMatch) {
        return answerToMatch.equals(this.answer);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = formatAnswer(answer);
    }

    public void setQuestion(CodingQuestion question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }
}
