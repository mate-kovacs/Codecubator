package com.codecool.poop.repository.quiz_repositories;

import com.codecool.poop.model.Rooms;
import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizAssignmentRepository extends JpaRepository<QuizAssignment, Integer> {
    public List<QuizAssignment> findQuizAssignmentsByRoom(Rooms room);
}
