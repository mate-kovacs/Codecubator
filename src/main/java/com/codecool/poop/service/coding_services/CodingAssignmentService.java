package com.codecool.poop.service.coding_services;

import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.repository.coding_repositories.CodingAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CodingAssignmentService {

    @Autowired
    private CodingAssignmentRepository codingAssignmentRepository;

    public void addCodingAssignment(CodingAssignment assignment) {
        codingAssignmentRepository.save(assignment);
    }

    public List<CodingAssignment> getAllCodingAssignment() {
        return codingAssignmentRepository.findAll();
    }

    public CodingAssignment getCodingAssignmentById(Integer id) {
        Optional<CodingAssignment> assignment = codingAssignmentRepository.findById(id);
        return assignment.orElse(null);
    }
}