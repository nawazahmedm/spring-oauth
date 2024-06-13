package com.javalearning.spring_oauth.controller;

import com.javalearning.spring_oauth.entity.UserInfo;
import com.javalearning.spring_oauth.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "Operations related to user management")
public class UserInfoController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Operation(summary = "Get all users")
    @GetMapping
    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }

    @Operation(summary = "Get a user by ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getUserById(@PathVariable Long id) {
        return userInfoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new user")
    @PostMapping
    public UserInfo createUser(@RequestBody UserInfo user) {
        return userInfoRepository.save(user);
    }

    @Operation(summary = "Update a user by ID")
    @PutMapping("/{id}")
    public ResponseEntity<UserInfo> updateUser(@PathVariable Long id, @RequestBody UserInfo userDetails) {
        return userInfoRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDetails.getUsername());
                    user.setPassword(userDetails.getPassword());
                    user.setRole(userDetails.getRole());
                    return ResponseEntity.ok(userInfoRepository.save(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a user by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userInfoRepository.findById(id)
                .map(user -> {
                    userInfoRepository.delete(user);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
