package com.safecornerscoffee.controller;

import com.safecornerscoffee.assembler.UserAssembler;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.dto.ErrorResponse;
import com.safecornerscoffee.dto.UserDTO;
import com.safecornerscoffee.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/join")
    public String joinPage() {
        return "join";
    }

    @PostMapping("/join")
    public String signUp(UserDTO signUpRequest, Model model, HttpSession httpSession) {
        User user = userService.signUp(signUpRequest);
        UserDTO sessionUser = UserAssembler.writeDTO(user);
        httpSession.setAttribute("user", sessionUser);
        return "index";
    }

    @GetMapping("/users")
    public String usersPage(Model model) {
        List<UserDTO> users = userService.getAllUsers()
                .stream()
                .map(UserAssembler::writeDTO)
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        return "user/users";
    }

    @GetMapping("/users/new")
    public String newUserPage() {
        return "user/new-user";
    }

    @GetMapping("/users/{userId}")
    public String getUser(@PathVariable Long userId, Model model) {
        User user = userService.getUser(userId);
        UserDTO dto = UserAssembler.writeDTO(user);
        model.addAttribute("user", user);
        return "user/edit-user";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleErrors(Exception e, Model model) {
        ErrorResponse error = new ErrorResponse(e.getMessage());
        model.addAttribute("error");
        return "error";
    }
}