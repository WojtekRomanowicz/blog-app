package com.example.blogapp.service;

import com.example.blogapp.model.jpa.User;
import com.example.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCRUDService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User createNewUser(User user) {
        user = userRepository.save(user);
        return user;
    }

    @Transactional
    public boolean deleteUser(String username)  {
        userRepository.deleteByUsername(username);
        return true;
    }
}