package com.safecornerscoffee.integration.controller;

import com.safecornerscoffee.medium.dto.UserDTO;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/web/WEB-INF/applicationContext.xml", "file:src/main/web/WEB-INF/dispatcher-servlet.xml"})
@WebAppConfiguration
@Transactional
public class UserControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Before
    public void beforeEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void signUpTest() throws Exception {
        String username = "coffee";
        String password = "coffee";
        String email = "coffee@safecornerscoffee.com";

        MockHttpServletRequestBuilder request = post("/signup")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", username)
                .param("password", password)
                .param("email", email);

        HttpSession session = mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getRequest()
                .getSession();

        UserDTO user = (UserDTO) session.getAttribute("user");
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("coffee");
        assertThat(user.getEmail()).isEqualTo("coffee@safecornerscoffee.com");
    }
}