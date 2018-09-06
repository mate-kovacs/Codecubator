package com.codecool.poop.controller;

import com.codecool.poop.model.User;
import com.codecool.poop.service.AssignmentService;
import com.codecool.poop.service.SessionService;
import com.codecool.poop.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@WebMvcTest(HTMLController.class)
public class HtmlControllerTest {

    @Autowired
    WebApplicationContext wac;
    @Autowired
    MockHttpSession session;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private SessionService sessionService;
    @MockBean
    private AssignmentService assignmentService;

    @InjectMocks
    private HTMLController htmlController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void registration_get_request_returns_template() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("registration"));
    }

    @Test
    public void request_to_index_without_session_redirects_to_login() throws Exception {
        when(sessionService.getCurrentUser())
                .thenReturn(null);
        mockMvc.perform(get("/"))
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    public void request_with_session_redirects_to_index() throws Exception {
        when(sessionService.getCurrentUser())
                .thenReturn(new User("Cili", "cili", "cili@cili.hu"));
        mockMvc.perform(get("/"))
                .andExpect(view().name("index"));
    }

    @Test
    public void logout_url_redirects_to_login_page() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    public void user_profile_url_without_session_redirects_to_login_page() throws Exception {
        when(sessionService.getCurrentUser())
                .thenReturn(null);
        mockMvc.perform(get("/user-profile"))
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    public void user_profile_url_with_session_redirects_to_user_profile() throws Exception {
        when(sessionService.getCurrentUser())
                .thenReturn(new User("Cili", "cili", "cili@cili.hu"));
        mockMvc.perform(get("/user-profile"))
                .andExpect(view().name("user_profile"));
    }

}