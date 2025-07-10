package com.socialmediaapp.repository;

import com.socialmediaapp.model.Like;
import com.socialmediaapp.model.User;
import com.socialmediaapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndPost(User user, Post post);
    Like findByUserAndPost(User user, Post post);
    long countByPost(Post post);
}
