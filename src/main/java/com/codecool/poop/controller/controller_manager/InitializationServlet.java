package com.codecool.poop.controller.controller_manager;

import com.codecool.poop.controller.Index;
import com.codecool.poop.controller.Login;
import com.codecool.poop.controller.Logout;
import com.codecool.poop.controller.Registration;
import com.codecool.poop.dao.UserManager;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;

public class InitializationServlet  extends HttpServlet{
    public void init() throws ServletException
    {
        UserManager userManager = new UserManager();

        Login servletLogin = new Login(userManager);
        Registration servletRegistration = new Registration(userManager);
        Index servletIndex = new Index();
        Logout servletLogout= new Logout();

        getServletContext().setAttribute("servletIndex", servletIndex);
        getServletContext().setAttribute("servletLogin", servletLogin);
        getServletContext().setAttribute("servletLogout", servletLogout);
        getServletContext().setAttribute("servletRegistration", servletRegistration);

    }
}
