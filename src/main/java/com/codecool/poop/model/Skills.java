package com.codecool.poop.model;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
public class Skills {
    PYTHON_BASIC (1, "Python basic", "Python basic language knowledge"),
    JAVA_BASIC (2, "Java basic", "Java basic language knowledge"),
    JAVASCRIPT_BASIC(3, "Javascript basic", "Javascript basic language knowledge"),
    HTML_BASIC (4, "HTML basic", "HTML basic language knowledge"),
    CSS_BASIC (5, "CSS basic", "CSS basic language knowledge"),
    OBJECT_ORIENTED_PROGRAMMING (6, "OOP", "Object Oriented Programming paradigm knowledge"),
    FUNCTIONAL_PROGRAMMING (7, "Functional Programming", "Functional PRogramming paradigm knowledge"),
    ALGORITHMS (8, "Algorythm", "Algorythm knowledge"),
    PATTERNS (9, "Patterns", "Programming patterns knowledge"),
    DATA_STRUCTURES (10, "Data structures", "Data structures knowledge");

    @Id
    private int skillId;
    public String name;
    public String description;

    Skills() {}

    Skills(int id, String name, String description) {
        this.skillId = id;
        this.name = name;
        this.description = description;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
