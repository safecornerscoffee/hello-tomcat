package com.safecornerscoffee.controller;

import com.safecornerscoffee.assembler.UserAssembler;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.dto.ErrorResponse;
import com.safecornerscoffee.dto.UserDTO;
import com.safecornerscoffee.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/register")
    public String signUpPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(UserDTO signUpRequest, Model model, HttpSession httpSession) {
        User user = userService.signUp(signUpRequest);
        UserDTO sessionUser = UserAssembler.writeDTO(user);
        httpSession.setAttribute("user", sessionUser);
        return "index";
    }

    @GetMapping("/users")
    public String usersPage(Model model) {
        try {
            List<User> users = userService.getAllUsers();
            List<UserDTO> dtos = users.stream()
                    .map(UserAssembler::writeDTO)
                    .collect(Collectors.toList());
            model.addAttribute("users", dtos);
            return "users";
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse(e.getMessage());
            model.addAttribute("error", error);
            return "error";
        }
    }

    @GetMapping("/users/{userId}")
    @ResponseBody
    public ResponseEntity<Object> getUser(@PathVariable Long userId, HttpServletResponse HttpResponse) {
        User user = userService.getUser(userId);
        UserDTO dto = UserAssembler.writeDTO(user);
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleErrors(Exception e, Model model) {
        ErrorResponse error = new ErrorResponse(e.getMessage());
        model.addAttribute("error");
        return "error";
    }
}
