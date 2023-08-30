package com.roadside.backend.service;

import com.roadside.backend.model.User;
import com.roadside.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserService {
    private UserRepo userRepo;
    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public void createNewUser(User user){
        if(userRepo.findByEmail(user.getEmail())==null){
            User newUser = new User();
            newUser.setId(String.valueOf(UUID.randomUUID()));
            newUser.setUsername(user.getUsername());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            userRepo.save(newUser);
            System.out.println("User saved successfully");
        }
        else {
            System.out.println("Email Already exist !");
        }
    }
}
