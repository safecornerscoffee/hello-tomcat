package com.safecornerscoffee.controller;

import com.safecornerscoffee.service.UserService;
import com.safecornerscoffee.service.dto.ErrorResponse;
import com.safecornerscoffee.service.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public String signUp(UserDTO userDTO, Model model) {
        try {
            userService.signUp(userDTO.getUsername(), userDTO.getEmail(), userDTO.getName(), userDTO.getPassword());
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/signin")
    public String signInPage() {
        return "signin";
    }

    @PostMapping("/signin")
    public String signIn(String email, String password, Model model, HttpSession httpSession) {
        try {
            UserDTO userDTO = userService.signIn(email, password);
            model.addAttribute("user", userDTO);
            httpSession.setAttribute("sessionUser", userDTO);
            return "main";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/users/{userId}")
    @ResponseBody
    public ResponseEntity<Object> getUser(@PathVariable Long userId, HttpServletResponse HttpResponse) {
        UserDTO user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleErrors(Exception e) {
        ErrorResponse error = new ErrorResponse(e.getMessage());
        return ResponseEntity
                .status(404)
                .body(error);
    }
}
