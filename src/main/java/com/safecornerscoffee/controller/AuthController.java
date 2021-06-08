package com.safecornerscoffee.controller;

import com.safecornerscoffee.assembler.UserAssembler;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.dto.ErrorResponse;
import com.safecornerscoffee.dto.UserDTO;
import com.safecornerscoffee.service.UserService;
import com.safecornerscoffee.util.AuthHelper;
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
    public String signInPage() {
        return "signin";
    }

    @PostMapping("/signin")
    public String signIn(UserDTO signUp, Model model, HttpSession httpSession) {
        try {
            User user = userService.signIn(signUp);

            String token = AuthHelper.generateToken(user.getUsername());

            UserDTO userWithToken = UserAssembler.writeDTO(user);
            userWithToken.setToken(token);

            model.addAttribute("user", userWithToken);
            httpSession.setAttribute("user", userWithToken);
            return "main";
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
