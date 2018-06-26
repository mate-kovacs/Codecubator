package com.codecool.poop.controller;

import com.codecool.poop.dao.CodingQuestManager;
import com.codecool.poop.dao.QuizQuestManager;
import com.codecool.poop.dao.UserManager;
import com.codecool.poop.model.User;
import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class HTMLAssignmentController {

    @Autowired
    private CodingQuestManager codingQuestManager;
    @Autowired
    private QuizQuestManager quizQuestManager;
    @Autowired
    private UserManager userManager;

    @RequestMapping(value = "/coding-assignment", method = RequestMethod.GET)
    public String renderCodingAssignment(Model model,
                                         HttpServletRequest request,
                                         @RequestParam("assignment") int id) {
        CodingAssignment assignment = codingQuestManager.getCodingAssignemntByID(id);

        HttpSession session = request.getSession();
        Map<String, Object> userData = (Map) session.getAttribute("user");
        User user = userManager.getUserByName(userData.get("user_name").toString());

        model.addAttribute("user_name", userData.get("user_name"));
        model.addAttribute("assignment", assignment);
        user.setHealthToMax();
//        setAchievedPointsToZero(session);
        return "assignments/coding_assignment";
    }

    @RequestMapping(value = "/quiz-assignment", method = RequestMethod.GET)
    public String renderQuizAssignment(Model model,
                                         HttpServletRequest request,
                                         @RequestParam("assignment") int id) {
        QuizAssignment assignment = quizQuestManager.getQuizAssignemntByID(id);

        HttpSession session = request.getSession();
        Map<String, Object> userData = (Map) session.getAttribute("user");
        User user = userManager.getUserByName(userData.get("user_name").toString());

        model.addAttribute("user_name", userData.get("user_name"));
        model.addAttribute("assignment", assignment);
//        setUserToMaxHealth(session);
//        setAchievedPointsToZero(session);
        return "assignments/quiz_assignment";
    }
}
