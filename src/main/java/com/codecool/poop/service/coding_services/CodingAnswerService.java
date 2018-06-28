package com.codecool.poop.service.coding_services;

import com.codecool.poop.model.assignments.coding.CodingAnswer;
import com.codecool.poop.repository.coding_repositories.CodingAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
