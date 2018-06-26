package com.codecool.poop.service.CodingServices;

import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.repository.CodingRepositories.CodingAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return codingAssignmentRepository.getOne(id);
    }
}
