package com.aneesh.usermanagement.repository;


import com.aneesh.usermanagement.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Find all posts by user
    List<Post> findByUserId(Long userId);
}