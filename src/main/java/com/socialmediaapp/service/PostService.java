package com.socialmediaapp.service;

import com.socialmediaapp.model.Post;
import com.socialmediaapp.model.User;

import java.util.List;

public interface PostService {
    Post createPost(String content, User user);
    List<Post> getPostsByUser(User user);

    Post getPostById(Long id);
    void deletePost(Long id);
    Post updatePost(Long id, String newContent, User user);

}
