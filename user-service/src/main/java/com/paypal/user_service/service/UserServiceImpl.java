package com.paypal.user_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.paypal.user_service.entity.User;
import com.paypal.user_service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User creataUser(User user) {
        return (User) userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
       return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

}
