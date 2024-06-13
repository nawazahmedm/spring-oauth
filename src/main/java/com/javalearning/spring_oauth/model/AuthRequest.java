package com.javalearning.spring_oauth.model;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
