package com.roadside.backend.controller;

import com.roadside.backend.exception.NotFoundException;
import com.roadside.backend.model.Login;
import com.roadside.backend.model.User;
import com.roadside.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

    private final UserService userService;

    // Constructor-based dependency injection
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to register a new user via a POST request
    @PostMapping
    public ResponseEntity<String> register(@RequestBody User user) {
        String result = userService.createNewUser(user);
        return ResponseEntity.ok(result);
    }

    // Endpoint for user login via a POST request
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        // Implement your login logic here
        boolean isAuthenticated = userService.authenticateUser(login.getUsername(), login.getPassword());

        if (isAuthenticated) {
            // Handle successful login
            return ResponseEntity.ok("Login successful");
        } else {
            // Handle unsuccessful login
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

    // Retrieve all users
    @GetMapping("/getAllUsers")
    public ResponseEntity<Iterable<User>> findAll() {
        Iterable<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Delete User By ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        String result = userService.deleteUserById(id); // Use the provided ID
        return ResponseEntity.ok(result);
    }

    // Find User By ID
    @PostMapping("/find")
    public ResponseEntity<?> getUserByEmail(@RequestBody String email) {
        // Validate the email input
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required.");
        }
        User userById = userService.findUserByEmail(email);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }
}
