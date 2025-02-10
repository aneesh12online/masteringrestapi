package com.aneesh.usermanagement.controller;

import com.aneesh.usermanagement.model.User;
import com.aneesh.usermanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "Operations for managing users")
class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    // Retrieve all users
    @Operation(summary = "Retrieve all users", description = "Fetches a list of all registered users")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Retrieve a user by ID
    @Operation(summary = "Retrieve user by id", description = "Retrieve user by id")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found")),HttpStatus.OK);
    }

    // Retrieve users by name (case-insensitive search)
    @Operation(summary = "Retrieve users by name (case-insensitive search)", description = "Retrieve users by name (case-insensitive search)")
    @GetMapping("/name/{name}")
    public ResponseEntity<List<User>> getUsersByName(@PathVariable String name) {
        return new ResponseEntity<>(userService.getUsersByName(name), HttpStatus.OK);
    }


    // Retrieve users by exact email
    @Operation(summary = "Retrieve users by exact email", description = "Retrieve users by exact email")
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user= userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @Operation(summary = "Create a new user", description = "Adds a new user to the system with validation")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
