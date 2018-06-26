package com.codecool.poop.repository;

import com.codecool.poop.model.assignments.mastery.MasteryAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasteryQuestRepository extends JpaRepository<MasteryAssignment, Long> {

    public MasteryAssignment findById(int id);
}
