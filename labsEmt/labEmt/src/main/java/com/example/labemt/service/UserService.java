package com.example.labemt.service;

import com.example.labemt.model.User;
import com.example.labemt.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface UserService extends UserDetailsService {

    User login(String username, String password);

    User register(String name, String surname, String email,
                  String username, String password, String passwordRepeat, Role role);

    List<User> findAll();

    User findById(Long id);

    void deleteById(Long id);

    User edit(Long id, String name, String surname, String email,
              String username, Role role);
}