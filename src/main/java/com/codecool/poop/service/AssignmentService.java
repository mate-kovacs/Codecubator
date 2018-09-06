package com.codecool.poop.service;

import com.codecool.poop.model.Rooms;
import com.codecool.poop.model.assignments.Assignment;
import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import com.codecool.poop.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    AssignmentRepository assignmentRepository;

    public List<Assignment> getAll() {
        return assignmentRepository.findAll();
    }

    public List<Assignment> getAllAssignmentByRoom(Rooms room) {
        return assignmentRepository.findAssignmentsByRoom(room);
    }

}
