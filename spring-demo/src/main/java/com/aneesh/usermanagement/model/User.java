package com.aneesh.usermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users") // Specifies table name in the database
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long userId;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, message = "Name must have at least 3 characters")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    @Column(unique = true) // Ensures email is unique
    private String email;

    // Default constructor (needed by JPA)
    public User() {}

    // Constructor for creating user objects
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() { return userId; }
    public void setId(Long id) { this.userId = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

}
