package com.rabiu.thriveagric.controller;

import com.rabiu.thriveagric.exception.ResourceNotFoundException;
import com.rabiu.thriveagric.repository.UserRepository;
import com.rabiu.thriveagric.model.User;
import com.rabiu.thriveagric.security.CurrentUser;
import com.rabiu.thriveagric.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
