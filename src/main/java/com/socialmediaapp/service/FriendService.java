package com.socialmediaapp.service;

import com.socialmediaapp.model.User;

import java.util.List;


public interface FriendService {
    void sendFriendRequest(User from, User to);
    void acceptFriendRequest(Long requestId);
    void declineFriendRequest(Long requestId);
    List<User> getFriends(User user);
    List<User> getIncomingRequests(User user);
    List<User> getOutgoingRequests(User user);
}
