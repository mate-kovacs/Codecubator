package com.codecool.poop.controller.controller_manager;

import com.codecool.poop.controller.Index;
import com.codecool.poop.controller.Login;
import com.codecool.poop.controller.Registration;
import com.codecool.poop.dao.CodingQuestManager;
import com.codecool.poop.dao.MasteryQuestManager;
import com.codecool.poop.dao.QuizQuestManager;
import com.codecool.poop.dao.UserManager;
import com.codecool.poop.db_initializer.DummyDBInitializer;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;

public class InitializationServlet  extends HttpServlet{
    public void init() throws ServletException
    {
        UserManager userManager = new UserManager();
        CodingQuestManager codingQuestManager = new CodingQuestManager();
        QuizQuestManager quizQuestManager = new QuizQuestManager();
        MasteryQuestManager masteryQuestManager = new MasteryQuestManager();

        Login servletLogin = new Login(userManager);
        Registration servletRegistration = new Registration(userManager);
        Index servletIndex = new Index();

        getServletContext().setAttribute("servletIndex", servletIndex);
        getServletContext().setAttribute("servletLogin", servletLogin);
        getServletContext().setAttribute("servletRegistration", servletRegistration);

        DummyDBInitializer dummyDBInitializer = new DummyDBInitializer(
                userManager,
                quizQuestManager,
                codingQuestManager,
                masteryQuestManager);
        dummyDBInitializer.initializeDummyDB();
    }
}
