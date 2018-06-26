package com.codecool.poop.repository.quiz_repositories;

import com.codecool.poop.model.assignments.quiz.QuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizAnswerRepository extends JpaRepository<QuizAnswer, Integer> {
}
