package com.codecool.poop.service.quiz_services;

import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import com.codecool.poop.repository.quiz_repositories.QuizAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizAssignmentService {

    @Autowired
    private QuizAssignmentRepository quizAssignmentRepository;

    public void addQuizAssignment(QuizAssignment assignment) {
        quizAssignmentRepository.save(assignment);
    }

    public List<QuizAssignment> getAllQuizAssignment() {
        return quizAssignmentRepository.findAll();
    }

    public QuizAssignment getQuizAssignmentById(Integer id) {
        Optional<QuizAssignment> assignment = quizAssignmentRepository.findById(id);
        return assignment.orElse(null);
    }
}
