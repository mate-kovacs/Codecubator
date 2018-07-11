package com.codecool.poop.service.quiz_services;

import com.codecool.poop.model.assignments.quiz.QuizAnswer;
import com.codecool.poop.repository.quiz_repositories.QuizAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizAnswerService {

    @Autowired
    private QuizAnswerRepository quizAnswerRepository;

    public void addQuizAnswer(QuizAnswer answer) {
        quizAnswerRepository.save(answer);
    }

    public List<QuizAnswer> getAllQuizAnswer() {
        return quizAnswerRepository.findAll();
    }

    public QuizAnswer getQuizAnswerById(Integer id) {
        return quizAnswerRepository.getOne(id);
    }
}
