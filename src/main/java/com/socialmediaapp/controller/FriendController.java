package com.socialmediaapp.controller;

import com.socialmediaapp.model.User;
import com.socialmediaapp.service.FriendService;
import com.socialmediaapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    @GetMapping("/friends")
    public String showFriends(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("friends", friendService.getFriends(user));
        model.addAttribute("incoming", friendService.getIncomingRequests(user));
        model.addAttribute("outgoing", friendService.getOutgoingRequests(user));
        return "friends";
    }

    @PostMapping("/friend/send")
    public String sendRequest(@RequestParam String toEmail,
                              @AuthenticationPrincipal UserDetails userDetails) {
        User from = userService.findByEmail(userDetails.getUsername());
        User to = userService.findByEmail(toEmail);
        if (to != null) {
            friendService.sendFriendRequest(from, to);
        }
        return "redirect:/friends";
    }

    @PostMapping("/friend/accept")
    public String acceptRequest(@RequestParam Long requestId) {
        friendService.acceptFriendRequest(requestId);
        return "redirect:/friends";
    }

    @PostMapping("/friend/decline")
    public String declineRequest(@RequestParam Long requestId) {
        friendService.declineFriendRequest(requestId);
        return "redirect:/friends";
    }
}
