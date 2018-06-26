//package com.codecool.poop.controller;
//
//import com.codecool.poop.dao.CodingQuestManager;
//import com.codecool.poop.dao.MasteryQuestManager;
//import com.codecool.poop.dao.QuizQuestManager;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.WebContext;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class AssignmentsController extends HttpServlet implements LoginHandler {
//
//    private QuizQuestManager quizQuestManager;
//    private MasteryQuestManager masteryQuestManager;
//    private CodingQuestManager codingQuestManager;
//
//    public AssignmentsController(QuizQuestManager quizQuestManager, CodingQuestManager codingQuestManager, MasteryQuestManager masteryQuestManager) {
//        this.quizQuestManager = quizQuestManager;
//        this.codingQuestManager = codingQuestManager;
//        this.masteryQuestManager = masteryQuestManager;
//    }
//
////    @Override
////    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
////        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
////        WebContext context = new WebContext(request, response, request.getServletContext());
////        HttpSession session = request.getSession();
////        if (!isUserLoggedIn(session)) {
////            response.sendRedirect("/");
////            return;
////        }
////        Map userData = (Map) session.getAttribute("user");
////        String uri = request.getRequestURI();
////        List assignments = new ArrayList();
////        String header = "";
////        switch (uri) {
////            case "/quiz-assignments":
////                assignments = quizQuestManager.getAllQuizAssignments();
////                header = "Quiz assignments";
////                break;
////            case "/coding-assignments":
////                assignments = codingQuestManager.getAllCodingAssignments();
////                header = "Coding assignments";
////                break;
////            case "/mastery-assignments":
////                assignments = masteryQuestManager.getAllAssignments();
////                header = "Mastery assignments";
////                break;
////        }
////        context.setVariable("user_name", userData.get("user_name"));
////        context.setVariable("assignments", assignments);
////        context.setVariable("header", header);
////        engine.process("assignments.html", context, response.getWriter());
////    }
//
////    @Override
////    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
////        System.out.println("Post received");
////    }
//}
