package com.vivek.stocksentry.controller;

import com.vivek.stocksentry.entity.User;
import com.vivek.stocksentry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    public User getCurrentUser(
            @AuthenticationPrincipal UserDetails userDetails) {

        return userRepository
                .findByUsername(
                        userDetails.getUsername())
                .orElse(null);
    }
}