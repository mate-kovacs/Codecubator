package com.codecool.poop.model.assignments.mastery;

import com.codecool.poop.model.Rooms;
import com.codecool.poop.model.Skills;
import com.codecool.poop.model.assignments.Assignment;
import com.codecool.poop.model.assignments.coding.CodingQuestion;
import com.codecool.poop.model.assignments.quiz.QuizQuestion;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.*;

@Entity
@DiscriminatorValue("MASTERY")
public class MasteryAssignment extends Assignment{

    @ManyToMany(mappedBy = "assignments", cascade = CascadeType.PERSIST)
    Set<CodingQuestion> codingQuestions = new HashSet<>();

    @ManyToMany(mappedBy = "assignments", cascade = CascadeType.PERSIST)
    List<QuizQuestion> quizQuestions = new ArrayList<>();

    public MasteryAssignment(String name,
                             String desciption,
                             Map<Skills, Integer> expReward,
                             Integer codeCoinReward,
                             List<QuizQuestion> quizQuestions,
                             Set<CodingQuestion> codingQuestions,
                             Rooms room){
        super(name, desciption, expReward, codeCoinReward, room);
        this.codingQuestions = codingQuestions;
        this.quizQuestions = quizQuestions;
        setQuestionReferences();
    }

    public MasteryAssignment(){
    }

    @Override
    public Integer getMaxPoints() {
        return 0;
    }

    private void setQuestionReferences(){
        for (QuizQuestion question: quizQuestions) {
            question.addAssignment(this);
        }
        for (CodingQuestion question: codingQuestions) {
            question.addAssignment(this);
        }
    }

    public Set<CodingQuestion> getCodingQuestions() {
        return codingQuestions;
    }

    public void setCodingQuestions(Set<CodingQuestion> codingQuestions) {
        this.codingQuestions = codingQuestions;
    }

    public List<QuizQuestion> getQuizQuestions() {
        return quizQuestions;
    }

    public void setQuizQuestions(List<QuizQuestion> quizQuestions) {
        this.quizQuestions = quizQuestions;
    }
}
