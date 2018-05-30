package com.codecool.poop.model;

public enum Skills {
    PYTHON_BASIC ("Python basic", "Python basic language knowledge"),
    JAVA_BASIC ("Java basic", "Java basic language knowledge"),
    JAVASCRIPT_BASIC("Javascript basic", "Javascript basic language knowledge"),
    HTML_BASIC ("HTML basic", "HTML basic language knowledge"),
    CSS_BASIC ("CSS basic", "CSS basic language knowledge"),
    OBJECT_ORIENTED_PROGRAMMING ("OOP", "Object Oriented Programming paradigm knowledge"),
    FUNCTIONAL_PROGRAMMING ("Functional Programming", "Functional PRogramming paradigm knowledge"),
    ALGORITHMS ("Algorythm", "Algorythm knowledge"),
    PATTERNS ("Patterns", "Programming patterns knowledge"),
    DATA_STRUCTURES ("Data structures", "Data structures knowledge");

    public String name;
    public String description;

    Skills(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
