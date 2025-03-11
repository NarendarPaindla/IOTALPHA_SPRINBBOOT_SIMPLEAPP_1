package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
         this.userRepository = userRepository;
         this.passwordEncoder = passwordEncoder;
    }
    
    public User saveUser(User user) {
         // Encrypt the password on registration.
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         return userRepository.save(user);
    }
    
    public List<User> getAllUsers() {
         return userRepository.findAll();
    }
    
    public Optional<User> getUserById(String id) {
         return userRepository.findById(id);
    }
    
    public void deleteUser(String id) {
         userRepository.deleteById(id);
    }
    
    public Optional<User> findByEmail(String email) {
         return userRepository.findByEmail(email);
    }
    
    // Custom login method (returns the user if credentials match, otherwise null)
    public User login(String email, String rawPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}