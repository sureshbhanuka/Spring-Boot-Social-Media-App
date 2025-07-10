package com.socialmediaapp.repository;

import com.socialmediaapp.model.Friendship;
import com.socialmediaapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    @Query("SELECT f.friend FROM Friendship f WHERE f.user.id = :userId")
    List<User> findFriendsByUser(Long userId);

    boolean existsByUserAndFriend(User user, User friend);
}
