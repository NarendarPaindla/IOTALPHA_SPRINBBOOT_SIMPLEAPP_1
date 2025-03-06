package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    //To Add the user to mongodb collection
    public User createUser(User user){
        return userRepository.save(user);
    }
        public List<User> getAllUsers(){
            return userRepository.findAll();
        }

}
