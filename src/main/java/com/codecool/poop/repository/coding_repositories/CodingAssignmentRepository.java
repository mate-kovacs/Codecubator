package com.codecool.poop.repository.coding_repositories;

import com.codecool.poop.model.assignments.coding.CodingAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodingAssignmentRepository extends JpaRepository<CodingAssignment, Integer> {
}
