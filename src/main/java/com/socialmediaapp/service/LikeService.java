package com.socialmediaapp.service;

import com.socialmediaapp.model.User;
import com.socialmediaapp.model.Post;

public interface LikeService {
    void likePost(User user, Post post);
    void unlikePost(User user, Post post);
    boolean isLiked(User user, Post post);
    long getLikeCount(Post post);
}
