package com.codecool.poop.repository;

import com.codecool.poop.model.Rooms;
import com.codecool.poop.model.assignments.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    List<Assignment> findAssignmentsByRoom(Rooms room);
}
