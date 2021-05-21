package com.safecornerscoffee.controller;

import com.safecornerscoffee.service.UserService;
import com.safecornerscoffee.service.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String usersPage(Model model) {
        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/signup")
    public String signUpPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(UserDTO userDTO) {
        userService.signUp(userDTO.getEmail(), userDTO.getName(), userDTO.getPassword());
        return "index";
    }

}
