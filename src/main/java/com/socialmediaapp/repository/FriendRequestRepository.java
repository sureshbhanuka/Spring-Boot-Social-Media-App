package com.socialmediaapp.repository;

import com.socialmediaapp.model.FriendRequest;
import com.socialmediaapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    boolean existsByFromUserAndToUser(User fromUser, User toUser);
    List<FriendRequest> findByToUser(User toUser);
    List<FriendRequest> findByFromUser(User fromUser);
}
