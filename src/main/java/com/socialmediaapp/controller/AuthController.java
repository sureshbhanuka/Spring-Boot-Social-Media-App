package com.socialmediaapp.controller;

import com.socialmediaapp.dto.UserRegistrationDto;
import com.socialmediaapp.service.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }


    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("user") UserRegistrationDto userDto,
                                      BindingResult result) {
        if (result.hasErrors()) return "register";

        if (userService.findByEmail(userDto.getEmail()) != null) {
            result.rejectValue("email", null, "Email already in use");
            return "register";
        }

        userService.registerUser(userDto);
        return "redirect:/login?registerSuccess=true";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
