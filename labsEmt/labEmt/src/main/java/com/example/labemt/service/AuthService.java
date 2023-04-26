package com.example.labemt.service;

import org.springframework.security.core.userdetails.User;

public interface AuthService {
    User login(String username, String password);
}
