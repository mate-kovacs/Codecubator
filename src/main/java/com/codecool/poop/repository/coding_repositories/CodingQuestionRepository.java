package com.codecool.poop.repository.coding_repositories;

import com.codecool.poop.model.assignments.coding.CodingQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodingQuestionRepository extends JpaRepository<CodingQuestion, Integer> {
}
