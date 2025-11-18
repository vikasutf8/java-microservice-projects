package com.paypal.user_service.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.paypal.user_service.dto.CreateWalletClient;
import org.springframework.stereotype.Service;

import com.paypal.user_service.entity.User;
import com.paypal.user_service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    private WalletClient walletClient;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.walletClient = walletClient;
    }


    @Override
    public User creataUser(User user) {
        User savedUser= (User) userRepository.save(user);

        try{
            CreateWalletClient client =new CreateWalletClient();
            client.setUserId(savedUser.getId());
            client.setCurrency("INR");
            walletClient.createWallet(client);

        } catch (Exception e) {
            userRepository.deleteAllById(Collections.singleton(savedUser.getId()));
            throw new RuntimeException(e);
        }
        return savedUser;
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
