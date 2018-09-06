package com.codecool.poop.repository.coding_repositories;

import com.codecool.poop.model.Rooms;
import com.codecool.poop.model.assignments.coding.CodingAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodingAssignmentRepository extends JpaRepository<CodingAssignment, Integer> {
    List<CodingAssignment> findAssignmentsByRoom(Rooms room);
}
