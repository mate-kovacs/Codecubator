package com.codecool.poop.controller;

import com.codecool.poop.model.User;
import com.codecool.poop.service.SessionService;
import com.codecool.poop.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@WebMvcTest(APIController.class)
public class APIControllerTest {

    @Autowired
    WebApplicationContext wac;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private SessionService sessionService;

    @InjectMocks
    private APIController apiController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void login_with_valid_user_returns_success() throws Exception {
        String password = BCrypt.hashpw("cccccccc", BCrypt.gensalt(12));
        User cili = new User("cili", password, "cili@cili.hu");
        when(userService.getUserByName("cili")).thenReturn(cili);

        mockMvc.perform(post("/login")
                .param("name", "cili")
                .param("password", "cccccccc"))
                .andExpect(content().string("success"));
    }

    @Test
    public void login_with_wrong_username_returns_not_matching() throws Exception {
        when(userService.getUserByName("cili")).thenThrow(new NullPointerException());

        mockMvc.perform(post("/login")
                .param("name", "cili")
                .param("password", "cccccccc"))
                .andExpect(content().string("Not matching"));
    }

    @Test
    public void login_with_wrong_password_returns_not_matching() throws Exception {
        String password = BCrypt.hashpw("cccccccc", BCrypt.gensalt(12));
        User cili = new User("cili", password, "cili@cili.hu");
        when(userService.getUserByName("cili")).thenReturn(cili);

        mockMvc.perform(post("/login")
                .param("name", "cili")
                .param("password", "aaaaaaaaaa"))
                .andExpect(content().string("Not matching"));
    }

    @Test
    public void registration_returns_false() throws Exception {
        when(userService.saveUser(any(User.class))).thenReturn(false);

        mockMvc.perform(post("/registration")
                .param("name", "cili")
                .param("password", "cccccccc")
                .param("email", "cili@cili.hu"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("{isNameAddedToDB:false}"));
    }


    @Test
    public void registration_returns_true() throws Exception {
        when(userService.saveUser(any(User.class))).thenReturn(true);

        mockMvc.perform(post("/registration")
                .param("name", "cili")
                .param("password", "cccccccc")
                .param("email", "cili@cili.hu"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("{isNameAddedToDB:true}"));
    }

}