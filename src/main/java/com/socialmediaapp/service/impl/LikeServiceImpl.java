package com.socialmediaapp.service.impl;

import com.socialmediaapp.model.*;
import com.socialmediaapp.repository.LikeRepository;
import com.socialmediaapp.service.LikeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepo;

    @Override
    public void likePost(User user, Post post) {
        if (!likeRepo.existsByUserAndPost(user, post)) {
            Like like = new Like();
            like.setUser(user);
            like.setPost(post);
            likeRepo.save(like);
        }
    }

    @Override
    public void unlikePost(User user, Post post) {
        Like like = likeRepo.findByUserAndPost(user, post);
        if (like != null) {
            likeRepo.delete(like);
        }
    }

    @Override
    public boolean isLiked(User user, Post post) {
        return likeRepo.existsByUserAndPost(user, post);
    }

    @Override
    public long getLikeCount(Post post) {
        return likeRepo.countByPost(post);
    }
}
