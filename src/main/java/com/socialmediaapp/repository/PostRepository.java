package com.socialmediaapp.repository;

import com.socialmediaapp.model.Post;
import com.socialmediaapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserOrderByTimestampDesc(User user);
}
