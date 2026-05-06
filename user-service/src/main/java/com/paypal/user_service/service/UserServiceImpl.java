package com.paypal.user_service.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.paypal.user_service.Client.WalletClient;
import com.paypal.user_service.dto.CreateWalletClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.paypal.user_service.entity.User;
import com.paypal.user_service.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final WalletClient walletClient;

//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//        this.walletClient = walletClient;
//    }


    @Override
    public User creataUser(User user) {
        User savedUser= (User) userRepository.save(user);

        try{
            CreateWalletClient client =new CreateWalletClient();
            client.setUserId(savedUser.getId());
            client.setCurrency("INR");


            System.out.println("Creating wallet for user: " + savedUser.getEmail() + " with userId: " + savedUser.getId());
            walletClient.createWallet(client);

        } catch (Exception e) {

            userRepository.deleteAllById(Collections.singleton(savedUser.getId()));
            throw new RuntimeException(e.getMessage());
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
        return Optional.of(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id)));
    }

}
