package com.safecornerscoffee.integration.controller;

import com.safecornerscoffee.assembler.UserAssembler;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.dto.UserDTO;
import com.safecornerscoffee.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/web/WEB-INF/applicationContext.xml", "file:src/main/web/WEB-INF/dispatcher-servlet.xml"})
@WebAppConfiguration
public class AuthControllerIntTest {

    MockMvc mockMvc;
    @Autowired
    WebApplicationContext wepApplicationContext;

    @Autowired
    UserService userService;
    User user;

    String username = "coffee";
    String email = "coffe@safecornerscoffee.com";
    String password = "coffee";
    String name = "coffee";
    String image = "coffee.png";

    @Before
    public void beforeEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wepApplicationContext).build();

        UserDTO signUpRequest = new UserDTO.UserDTOBuilder().username(username).email(email).password(password)
                .profileName(name).profileImage(image).build();
        user = userService.signUp(signUpRequest);
    }

    @After
    public void afterEach() {
        userService.dropUser(UserAssembler.writeDTO(user));
    }

    @Test
    public void signInTest() throws Exception {

        MockHttpServletRequestBuilder request = post("/signin")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", username)
                .param("password", password);
        HttpSession session = mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andReturn()
                .getRequest()
                .getSession();

        assertThat(session).isNotNull();
        assertThat(session.getAttribute("user")).isNotNull();
    }

    @Test
    public void logoutTest() throws Exception {

        MockHttpServletRequestBuilder request = post("/logout")
                .sessionAttr("user", UserAssembler.writeDTO(user));

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(request().sessionAttributeDoesNotExist("user"));
    }

}
