package com.aneesh.usermanagement.service;

import com.aneesh.usermanagement.model.Post;
import com.aneesh.usermanagement.model.User;
import com.aneesh.usermanagement.repository.PostRepository;
import com.aneesh.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsByUser(long userId) {
        return postRepository.findByUserId(userId);
    }

    public Post createPost(long userId,Post newPost){
        User user= userRepository.getReferenceById(userId);
        newPost.setUser(user);
        return  postRepository.save(newPost);
    }
}
