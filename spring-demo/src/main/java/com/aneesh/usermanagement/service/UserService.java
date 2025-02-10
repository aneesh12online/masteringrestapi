package com.aneesh.usermanagement.service;

import com.aneesh.usermanagement.model.User;
import com.aneesh.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userDao) {
        this.userRepository = userDao;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }


    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User addUser(User user) {
        // Check if email already exists
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with email " + user.getEmail() + " already exists.");
        }
        return userRepository.save(user);
    }
}
