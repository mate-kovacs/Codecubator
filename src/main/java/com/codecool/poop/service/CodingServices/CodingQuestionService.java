package com.codecool.poop.service.CodingServices;

import com.codecool.poop.model.assignments.coding.CodingQuestion;
import com.codecool.poop.repository.CodingRepositories.CodingQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodingQuestionService {

    @Autowired
    private CodingQuestionRepository codingQuestionRepository;

    public void addCodingQuestion(CodingQuestion assignment) {
        codingQuestionRepository.save(assignment);
    }

    public List<CodingQuestion> getAllCodingQuestion() {
        return codingQuestionRepository.findAll();
    }

    public CodingQuestion getCodingQuestionById(Integer id) {
        return codingQuestionRepository.getOne(id);
    }
}
