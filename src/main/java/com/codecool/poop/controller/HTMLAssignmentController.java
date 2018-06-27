package com.codecool.poop.controller;

import com.codecool.poop.model.User;
import com.codecool.poop.model.assignments.Assignment;
import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import com.codecool.poop.service.SessionService;
import com.codecool.poop.service.coding_services.CodingAssignmentService;
import com.codecool.poop.service.quiz_services.QuizAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HTMLAssignmentController {

    @Autowired
    private CodingAssignmentService codingAssignmentService;
    @Autowired
    private QuizAssignmentService quizAssignmentService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/coding-assignment", method = RequestMethod.GET)
    public String renderCodingAssignment(Model model,
                                         @RequestParam("assignment_id") int id) {
        if (sessionService.getCurrentUser() == null) {
            return "redirect:/login";
        }
        CodingAssignment assignment = codingAssignmentService.getCodingAssignmentById(id);
        baseSettingsForAssignment(model, assignment);
        return "assignments/coding_assignment";
    }

    @RequestMapping(value = "/quiz-assignment", method = RequestMethod.GET)
    public String renderQuizAssignment(Model model,
                                         @RequestParam("assignment_id") int id) {
        if (sessionService.getCurrentUser() == null) {
            return "redirect:/login";
        }
        QuizAssignment assignment = quizAssignmentService.getQuizAssignmentById(id);
        baseSettingsForAssignment(model, assignment);
        return "assignments/quiz_assignment";
    }

    private void baseSettingsForAssignment(Model model, Assignment assignment){
        User user = sessionService.getCurrentUser();
        model.addAttribute("user_name", user.getUsername());
        model.addAttribute("assignment", assignment);
        user.setHealthToMax();
        sessionService.setCurrentPoints(0);
    }
}
