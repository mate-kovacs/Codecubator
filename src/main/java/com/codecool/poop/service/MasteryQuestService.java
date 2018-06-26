package com.codecool.poop.service;

import com.codecool.poop.model.assignments.mastery.MasteryAssignment;
import com.codecool.poop.repository.MasteryQuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MasteryQuestService {

    @Autowired
    private MasteryQuestRepository masteryQuestRepository;

    public void saveMasteryAssignment(MasteryAssignment assignment){
        masteryQuestRepository.save(assignment);
    }

    public MasteryAssignment getMasteryAssignmenttById(Integer id){
        return masteryQuestRepository.findById(id);
    }

    public List getAllAssignments(){
        return masteryQuestRepository.findAll();
    }


}
