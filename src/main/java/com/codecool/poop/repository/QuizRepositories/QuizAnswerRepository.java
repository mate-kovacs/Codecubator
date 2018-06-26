package com.codecool.poop.repository.QuizRepositories;

import com.codecool.poop.model.assignments.quiz.QuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizAnswerRepository extends JpaRepository<QuizAnswer, Integer> {
}
