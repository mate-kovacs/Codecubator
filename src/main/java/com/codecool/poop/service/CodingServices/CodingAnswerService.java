package com.codecool.poop.service.CodingServices;

import com.codecool.poop.model.assignments.coding.CodingAnswer;
import com.codecool.poop.repository.CodingRepositories.CodingAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CodingAnswerService {

    @Autowired
    private CodingAnswerRepository codingAnswerRepository;

    public void addCodingAnswer(CodingAnswer assignment) {
        codingAnswerRepository.save(assignment);
    }

    public List<CodingAnswer> getAllCodingAnswer() {
        return codingAnswerRepository.findAll();
    }

    public CodingAnswer getCodingAnswerById(Integer id) {
        return codingAnswerRepository.getOne(id);
    }
}
