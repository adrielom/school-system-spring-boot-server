package com.example.demo.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }

    public void addUser(User user) {
        Optional<User> searchedUserByName = userRepository.findUserByName(user.getName());
        Optional<User> searchedUserByEmail = userRepository.findUserByEmail(user.getEmail());

        if (searchedUserByName.isPresent() || searchedUserByEmail.isPresent())
            throw new IllegalStateException("user already exists");

        userRepository.save(user);
    }
}
