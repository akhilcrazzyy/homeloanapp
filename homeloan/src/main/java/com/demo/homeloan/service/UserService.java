package com.demo.homeloan.service;

import com.demo.homeloan.entity.User;

import java.util.List;

public interface UserService {
    User findByEmail(String email);

    boolean authenticateUser(String email, String password);

    void registerUser(User user);

    List<User> getAllUsers();
}
