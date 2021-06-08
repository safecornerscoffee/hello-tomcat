package com.safecornerscoffee.controller;

import com.safecornerscoffee.assembler.UserAssembler;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.dto.ErrorResponse;
import com.safecornerscoffee.dto.UserDTO;
import com.safecornerscoffee.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String usersPage(Model model) {
        List<User> users = userService.getAllUsers();
        List<UserDTO> dtos = users.stream()
                .map(UserAssembler::writeDTO)
                .collect(Collectors.toList());
        model.addAttribute("users", dtos);
        return "users";
    }

    @GetMapping("/signup")
    public String signUpPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(UserDTO userDTO, Model model) {
        try {
            userService.signUp(userDTO);
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
    public String signIn(UserDTO signUp, Model model, HttpSession httpSession) {
        try {
            User user = userService.signIn(signUp);

            String token = generateJWT(user.getUsername());

            UserDTO userWithToken = UserAssembler.writeDTO(user);
            userWithToken.setToken(token);

            model.addAttribute("user", userWithToken);
            httpSession.setAttribute("sessionUser", userWithToken);
            return "main";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    private String generateJWT(String email) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        return Jwts.builder().setSubject(email).signWith(key).compact();
    }

    @GetMapping("/users/{userId}")
    @ResponseBody
    public ResponseEntity<Object> getUser(@PathVariable Long userId, HttpServletResponse HttpResponse) {
        User user = userService.getUser(userId);
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
