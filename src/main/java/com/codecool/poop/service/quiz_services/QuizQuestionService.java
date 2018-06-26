package com.codecool.poop.service.quiz_services;

import com.codecool.poop.model.assignments.quiz.QuizQuestion;
import com.codecool.poop.repository.quiz_repositories.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuizQuestionService {

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    public void addQuizQuestion(QuizQuestion assignment) {
        quizQuestionRepository.save(assignment);
    }

    public List<QuizQuestion> getAllQuizQuestion() {
        return quizQuestionRepository.findAll();
    }

    public QuizQuestion getQuizQuestionById(Integer id) {
        return quizQuestionRepository.getOne(id);
    }
}
