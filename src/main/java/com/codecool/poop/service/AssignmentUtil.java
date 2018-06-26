//package com.codecool.poop.service;
//
//import com.codecool.poop.model.User;
//
//import javax.servlet.http.HttpSession;
//
//public class AssignmentUtil {
//
//    private boolean isUserDead(User user, HttpSession session, boolean correctAnswer) {
//        boolean death = false;
//
//        if (!correctAnswer) {
//            userLoseHealth(session);
//            if (user.getHealth() == 0) {
//                death = true;
//            }
//        }
//        return death;
//    }
//}
