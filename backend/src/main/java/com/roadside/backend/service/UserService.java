package com.roadside.backend.service;

import com.roadside.backend.model.User;
import com.roadside.backend.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class); // Create a logger

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    // Method to create new user
    public String createNewUser(User user) {
        // Check if a user with the same email already exist
        if (userRepo.findByEmail(user.getEmail()) == null) {
            // Create new user instance
            User newUser = new User();
            newUser.setId(String.valueOf(UUID.randomUUID()));
            newUser.setUsername(user.getUsername());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            // Save the new user to the repository
            userRepo.save(newUser);
            return "User saved successfully";
        } else {
            return "Email Already exist !";
        }
    }

    // Return all users
    public Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Authenticates a user based on the provided username and password.
    public Boolean authenticateUser(String username, String password) {
        try {
            User user = userRepo.findByUsername(username);
            if (user != null) {
                // Perform additional authentication logic (compare passwords, etc.)
                if (user.getPassword().equals(password)) {
                    LOGGER.warn("User: <" + username + "> successfully authenticated.");
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            // Use the logger to log the error
            LOGGER.error("Error during user authentication: " + e.getMessage(), e);
            return false; // Indicate authentication failure due to exception
        }
    }

    // Delete user
    public String deleteUserById(String id) {
        try {
            userRepo.deleteById(id);
            LOGGER.warn("User with ID " + id + " was deleted successfully.");
            return "User with ID " + id + " was deleted successfully";
        }
        catch (Exception e){
            // Use the logger to log the error and throw it to the calling method
            LOGGER.error("Error while deleting user with ID: ", id,e);
            throw new RuntimeException("Error while deleting user", e);
        }
    }

    // Get user by Email
    public User findUserByEmail(String email){
        User user = Optional.ofNullable(userRepo.findByEmail(email))
                .orElseThrow(() -> new RuntimeException("User not found."));
        LOGGER.info(String.format("Email Founded: %s",email));
        return user;
    }
}