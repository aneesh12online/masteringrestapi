package com.aneesh.usermanagement.controller;

import com.aneesh.usermanagement.model.Post;
import com.aneesh.usermanagement.model.User;
import com.aneesh.usermanagement.service.PostService;
import com.aneesh.usermanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@Tag(name = "Post Management", description = "Operations for managing Post")
class PostController {

    private final PostService postService;

    PostController(PostService postService) {
        this.postService = postService;
    }

    // Get all posts
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUser(@PathVariable Long userId) {
        return postService.getPostsByUser(userId);
    }

    // Create a new post for a user
    @PostMapping("/user/{userId}")
    public ResponseEntity<Post> createPost(@PathVariable Long userId, @Valid @RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(userId,post));
    }
}
