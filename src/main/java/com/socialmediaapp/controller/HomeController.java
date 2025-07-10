package com.socialmediaapp.controller;

import com.socialmediaapp.model.User;
import com.socialmediaapp.service.PostService;
import com.socialmediaapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("posts", postService.getPostsByUser(user));
        model.addAttribute("username", user.getFirstName());
        return "home";
    }

    @PostMapping("/post")
    public String createPost(@RequestParam("content") String content,
                             @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        postService.createPost(content, user);
        return "redirect:/home";
    }
}
