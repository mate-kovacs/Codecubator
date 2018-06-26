package com.codecool.poop.repository;

import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizAssignmentRepository extends JpaRepository<QuizAssignment, Integer> {
}
