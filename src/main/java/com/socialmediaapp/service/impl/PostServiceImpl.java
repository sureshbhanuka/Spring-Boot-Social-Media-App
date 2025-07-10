package com.socialmediaapp.service.impl;

import com.socialmediaapp.model.Post;
import com.socialmediaapp.model.User;
import com.socialmediaapp.repository.PostRepository;
import com.socialmediaapp.service.PostService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Data
public class PostServiceImpl implements PostService {

    @Autowired private PostRepository postRepo;

    @Override
    public Post createPost(String content, User user) {
        Post post = new Post();
        post.setContent(content);
        post.setTimestamp(LocalDateTime.now());
        post.setUser(user);
        return postRepo.save(post);
    }

    @Override
    public List<Post> getPostsByUser(User user) {
        return postRepo.findByUserOrderByTimestampDesc(user);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepo.findById(id).orElse(null);
    }

    @Override
    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }

    @Override
    public Post updatePost(Long id, String newContent, User user) {
        Post post = getPostById(id);
        if (post != null && post.getUser().getId().equals(user.getId())) {
            post.setContent(newContent);
            return postRepo.save(post);
        }
        return null;
    }

}
