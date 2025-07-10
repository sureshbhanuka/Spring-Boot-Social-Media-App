package com.socialmediaapp.service.impl;

import com.socialmediaapp.model.*;
import com.socialmediaapp.repository.*;
import com.socialmediaapp.service.FriendService;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class FriendServiceImpl implements FriendService {

    @Autowired private FriendRequestRepository requestRepo;
    @Autowired private FriendshipRepository friendshipRepo;

    @Override
    public void sendFriendRequest(User from, User to) {
        if (!requestRepo.existsByFromUserAndToUser(from, to) && !from.equals(to)) {
            FriendRequest request = new FriendRequest();
            request.setFromUser(from);
            request.setToUser(to);
            requestRepo.save(request);
        }
    }

    @Override
    public void acceptFriendRequest(Long requestId) {
        FriendRequest request = requestRepo.findById(requestId).orElseThrow();
        User from = request.getFromUser();
        User to = request.getToUser();

        friendshipRepo.save(new Friendship(from, to));
        friendshipRepo.save(new Friendship(to, from));
        requestRepo.delete(request);
    }

    @Override
    public void declineFriendRequest(Long requestId) {
        requestRepo.deleteById(requestId);
    }

    @Override
    public List<User> getFriends(User user) {
        return friendshipRepo.findFriendsByUser(user.getId());
    }

    @Override
    public List<User> getIncomingRequests(User user) {
        return requestRepo.findByToUser(user)
                .stream().map(FriendRequest::getFromUser).toList();
    }

    @Override
    public List<User> getOutgoingRequests(User user) {
        return requestRepo.findByFromUser(user)
                .stream().map(FriendRequest::getToUser).toList();
    }
}
