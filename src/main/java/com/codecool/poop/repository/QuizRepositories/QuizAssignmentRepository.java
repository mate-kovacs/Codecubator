package com.codecool.poop.repository.QuizRepositories;

import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizAssignmentRepository extends JpaRepository<QuizAssignment, Integer> {
}
