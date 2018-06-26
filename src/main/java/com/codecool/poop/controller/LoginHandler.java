package com.codecool.poop.controller;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface LoginHandler {
    default boolean isUserLoggedIn(HttpSession session) throws IOException {
        if (session.getAttribute("user") != null) {
            return true;
        } else {
            return false;
        }
    }
}
