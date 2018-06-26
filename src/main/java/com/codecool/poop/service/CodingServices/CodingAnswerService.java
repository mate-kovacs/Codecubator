package com.codecool.poop.service.CodingServices;

import com.codecool.poop.model.assignments.coding.CodingAnswer;
import com.codecool.poop.repository.CodingRepositories.CodingAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CodingAnswerService {

    @Autowired
    private CodingAnswerRepository codingAnswerRepository;

    public void addQuizAssignment(CodingAnswer assignment) {
        codingAnswerRepository.save(assignment);
    }

    public List<CodingAnswer> getAllCodingAssignment() {
        return codingAnswerRepository.findAll();
    }

    public CodingAnswer getCodingAssignmentById(Integer id) {
        return codingAnswerRepository.getOne(id);
    }
}
