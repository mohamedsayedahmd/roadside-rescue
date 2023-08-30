package com.roadside.backend.controller;

import com.roadside.backend.model.User;
import com.roadside.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public void findAll(@RequestBody User user){
        userService.createNewUser(user);
    }

}
