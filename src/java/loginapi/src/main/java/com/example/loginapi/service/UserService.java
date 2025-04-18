package com.example.loginapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loginapi.model.User;
import com.example.loginapi.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return false;  // 用户名已存在
        }
        userRepository.save(user);
        return true;
    }

    public boolean login(User user) {
        return userRepository.existsByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
