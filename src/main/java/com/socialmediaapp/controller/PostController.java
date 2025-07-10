package com.socialmediaapp.controller;

import com.socialmediaapp.model.User;
import com.socialmediaapp.model.Post;
import com.socialmediaapp.service.PostService;
import com.socialmediaapp.service.UserService;
import com.socialmediaapp.service.LikeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    // Display current user's posts
    @GetMapping
    public String showPosts(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("posts", postService.getPostsByUser(user));
        model.addAttribute("username", user.getFirstName());
        return "posts"; // You can also return "home" if posts are on the home page
    }

    // Handle post creation
    @PostMapping("/create")
    public String createPost(@RequestParam("content") String content,
                             @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        postService.createPost(content, user);
        return "redirect:/posts";
    }

    // (Optional) Delete a post
    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id,
                             @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        Post post = postService.getPostById(id);

        if (post != null && post.getUser().getId().equals(user.getId())) {
            postService.deletePost(id);
        }

        return "redirect:/posts";
    }

    // GET form to update post
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        Post post = postService.getPostById(id);
        if (post != null && post.getUser().getId().equals(user.getId())) {
            model.addAttribute("post", post);
            return "edit_post";
        }
        return "redirect:/posts";
    }

    // POST updated content
    @PostMapping("/update")
    public String updatePost(@RequestParam Long id, @RequestParam String content, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        postService.updatePost(id, content, user);
        return "redirect:/posts";
    }

    @Autowired private LikeService likeService;

    @PostMapping("/like/{id}")
    public String likePost(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        Post post = postService.getPostById(id);
        likeService.likePost(user, post);
        return "redirect:/posts";
    }

    @PostMapping("/unlike/{id}")
    public String unlikePost(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        Post post = postService.getPostById(id);
        likeService.unlikePost(user, post);
        return "redirect:/posts";
    }

}
