package com.codecool.poop.controller;


import com.codecool.poop.model.Rooms;
import com.codecool.poop.model.Skills;
import com.codecool.poop.model.User;
import com.codecool.poop.model.assignments.Assignment;
import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import com.codecool.poop.service.AssignmentService;
import com.codecool.poop.service.SessionService;
import com.codecool.poop.service.UserService;
import com.codecool.poop.service.coding_services.CodingAssignmentService;
import com.codecool.poop.service.quiz_services.QuizAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;


@Controller
public class HTMLController {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private QuizAssignmentService quizAssignmentService;

    @Autowired
    private CodingAssignmentService codingAssignmentService;

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping(value = "/registration")
    public String registrationPage() {
        return "registration";
    }

    @GetMapping(value = "/")
    public String indexPage(Model model) {
        if (sessionService.getCurrentUser() == null) {
            return "redirect:/login";
        }
        model.addAttribute("user_name", sessionService.getCurrentUser().getUsername());
        return "index";
    }

    @GetMapping(value = "/user-profile")
    public String userProfilePage(Model model) {
        if (sessionService.getCurrentUser() == null) {
            return "redirect:/login";
        }
        User user = sessionService.getCurrentUser();
        Map<Skills, Integer> skills = user.getExperiences();
        model.addAttribute("user_name", user.getUsername());
        model.addAttribute("skills", skills);
        return "user_profile";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        sessionService.setCurrentUser(null);
        return "redirect:/login";
    }

    @GetMapping(value = "/{room}")
    public String room(@PathVariable("room") String room, Model model) {
        if (sessionService.getCurrentUser() == null) {
            return "redirect:/login";
        }
        Rooms openedRoom = null;
        for (Rooms enumRoom : Rooms.values()) {
            if (enumRoom.name.equals(room)) {
                openedRoom = enumRoom;
            }
        }
        if (openedRoom == null) {
            return "redirect:/";
        }
//        List<Assignment> assignments = assignmentService.getAllAssignmentByRoom(openedRoom);
        List<QuizAssignment> quizAssignments = quizAssignmentService.getQuizAssignmnetsByRoom(openedRoom);
        List<CodingAssignment> codingAssignments = codingAssignmentService.getCodingAssignmnetsByRoom(openedRoom);
        model.addAttribute("user_name", sessionService.getCurrentUser().getUsername());
        model.addAttribute("quizAssignments", quizAssignments);
        model.addAttribute("codingAssignments", codingAssignments);
        model.addAttribute("roomName", openedRoom.name);
        return "room";
    }


}
