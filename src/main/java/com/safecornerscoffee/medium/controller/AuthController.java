package com.safecornerscoffee.medium.controller;

import com.safecornerscoffee.medium.domain.User;
import com.safecornerscoffee.medium.dto.ErrorResponse;
import com.safecornerscoffee.medium.dto.UserDTO;
import com.safecornerscoffee.medium.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/session")
    public String signIn(UserDTO signUp, Model model, HttpSession httpSession) {
        try {
            User user = userService.signIn(signUp);

            return "redirect:/";
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse(e.getMessage());
            model.addAttribute("error", error);
            return "error";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
