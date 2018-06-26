package com.codecool.poop.repository.CodingRepositories;

import com.codecool.poop.model.assignments.coding.CodingAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodingAssignmentRepository extends JpaRepository<CodingAssignment, Integer> {
}
