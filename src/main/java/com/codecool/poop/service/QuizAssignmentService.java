package com.codecool.poop.service;

import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import com.codecool.poop.repository.QuizAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizAssignmentService {

    private QuizAssignmentRepository quizAssignment;

    public void addQuizAssignment(QuizAssignment assignment) {
        quizAssignment.save(assignment);
    }

    public List<QuizAssignment> getAllCodingAssignment() {
        return quizAssignment.findAll();
    }

    public QuizAssignment getCodingAssignmentById(Integer id) {
        return quizAssignment.getOne(id);
    }
}
