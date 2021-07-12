package com.safecornerscoffee.medium.web;

import com.safecornerscoffee.medium.domain.Profile;
import com.safecornerscoffee.medium.domain.User;
import com.safecornerscoffee.medium.dto.UserDTO;
import com.safecornerscoffee.medium.service.UserService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    @InjectMocks
    AuthController authController;

    @Mock
    UserService userService;

    MockMvc mockMvc;
    MockHttpSession mockHttpSession;

    @Before
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        mockHttpSession = new MockHttpSession();
    }

    @Test
    public void signIn() throws Exception {
        Long userId = 1L;
        String username = "coffee";
        String password = "coffee";
        String email = "coffee@safecornerscoffee.com";
        Profile profile = new Profile("coffee", "coffee.png");

        User user = new User(userId, username, password, email, profile);

        given(userService.signIn(any(UserDTO.class))).willReturn(user);

        MockHttpServletRequestBuilder request = post("/signin")
                .session(mockHttpSession)
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

        verify(userService).signIn(any(UserDTO.class));
        assertThat(session.getAttribute("user")).isNotNull();
        UserDTO sessionUser = (UserDTO) session.getAttribute("user");
    }

    @Test
    public void logout() throws Exception {
        Long userId = 1L;
        String username = "coffee";
        String password = "coffee";
        String email = "coffee@safecornerscoffee.com";
        UserDTO user = new UserDTO.UserDTOBuilder().id(userId).username(username).password(password).email(email).build();

        mockHttpSession.setAttribute("user", user);

        MockHttpServletRequestBuilder request = post("/logout")
                .session(mockHttpSession);

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(request().sessionAttributeDoesNotExist("user"));
    }
}