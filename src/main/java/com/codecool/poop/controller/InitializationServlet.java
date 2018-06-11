package com.codecool.poop.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;

public class InitializationServlet  extends HttpServlet{
    public void init() throws ServletException
    {
        Login servletLogin = new Login("this is servlet A");

        getServletContext().setAttribute("servletLogin", servletLogin);

    }
}
