package com.codecool.poop.controller;

import com.codecool.poop.dao.UserManager;
import com.codecool.poop.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LoginTest {
    private HttpServletRequest requestMock;
    private HttpServletResponse responseMock;
    private UserManager userManagerMock;
    private Login loginInstance;
    private User userMock;
    private HttpSession sessionMock;
    private PrintWriter writerMock;

    @BeforeEach
    void setup() {
        this.requestMock = mock(HttpServletRequest.class);
        this.responseMock = mock(HttpServletResponse.class);
        this.userManagerMock = mock(UserManager.class);
        this.userMock = mock(User.class);
        this.sessionMock = mock(HttpSession.class);
        this.writerMock = mock(PrintWriter.class);
        this.loginInstance = new Login(userManagerMock);
    }

    @Test
    void Should_Verify_IfGoodUsernameAndPassword() throws IOException {

        when(requestMock.getParameter("password")).
                thenReturn("password");
        when(requestMock.getSession()).
                thenReturn(sessionMock);

        when(userMock.getUsername())
                .thenReturn("user");
        when(userMock.getId())
                .thenReturn(1L);
        String password = BCrypt.hashpw("password", BCrypt.gensalt(12));
        when(userMock.getPassword())
                .thenReturn(password);

        when(userManagerMock.getUserByName(any(String.class)))
                .thenReturn(userMock);

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("user_name", userMock.getUsername());
        userMap.put("user_id", userMock.getId());

        loginInstance.doPost(requestMock, responseMock);
        verify(sessionMock).setAttribute("user", userMap);
    }

    @Test
    void Should_Verify_IfWrongPassword() throws IOException {

        when(requestMock.getParameter("password")).
                thenReturn("wrongpassword");

        String password = BCrypt.hashpw("password", BCrypt.gensalt(12));
        when(userMock.getPassword())
                .thenReturn(password);

        when(userManagerMock.getUserByName(any(String.class)))
                .thenReturn(userMock);

        when(responseMock.getWriter())
                .thenReturn(writerMock);

        loginInstance.doPost(requestMock, responseMock);
        verify(writerMock).print("Not matching");
    }

    @Test
    void Should_Verify_NonExistingUsername() throws IOException {

        when(requestMock.getParameter("password")).
                thenReturn("password");

        String password = BCrypt.hashpw("password", BCrypt.gensalt(12));
        when(userMock.getPassword())
                .thenReturn(password);

        when(userManagerMock.getUserByName(any(String.class)))
                .thenThrow(new NoResultException());

        when(responseMock.getWriter())
                .thenReturn(writerMock);

        loginInstance.doPost(requestMock, responseMock);
        verify(writerMock).print("Not matching");
    }
}