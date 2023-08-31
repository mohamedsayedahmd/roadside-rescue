package com.roadside.backend.service;

import com.roadside.backend.model.User;
import com.roadside.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
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
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            // Handle the exception appropriately (log it, throw custom exception, etc.)
            System.err.println("Error during user authentication: " + e.getMessage());
            return false; // Indicate authentication failure due to exception
        }
    }

    // Delete user
    public String deleteUser(String id) {
        try {
            userRepo.deleteById(id);
            return "User with ID " + id + " was deleted successfully";
        }
        catch (Exception e){
            // Handle the exception and throw it to the calling method
            throw new RuntimeException("Error while deleting user", e);
        }
    }
}