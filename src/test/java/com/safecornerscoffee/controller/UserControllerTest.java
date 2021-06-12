package com.safecornerscoffee.controller;

import com.safecornerscoffee.assembler.UserAssembler;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.dto.UserDTO;
import com.safecornerscoffee.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    UserService userService;
    @InjectMocks
    UserController userController;

    MockMvc mockMvc;
    MockHttpSession mockHttpSession;

    @Before
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        mockHttpSession = new MockHttpSession();
    }

    @Test
    public void shouldCreateNewUser() throws Exception {
        String username = "coffee";
        String password = "coffee";
        String email = "coffee@safecornerscoffee.com";

        User user = new User.UserBuilder(1L, username, email, password).build();

        given(userService.signUp(any(UserDTO.class))).willReturn(user);
        UserDTO test = UserAssembler.writeDTO(user);

        MockHttpServletRequestBuilder request = post("/signup")
                .session(mockHttpSession)
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

        verify(userService).signUp(any(UserDTO.class));
        UserDTO sessionUser = (UserDTO) session.getAttribute("user");
        assertThat(sessionUser).isNotNull();

    }
}