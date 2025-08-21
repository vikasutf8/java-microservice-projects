package com.paypal.user_service.service;


import java.util.List;
import java.util.Optional;

import com.paypal.user_service.entity.User;

public interface UserService {

    User creataUser(User user);

    Optional<User> findByEmail(String email);
    Optional<User> getUserById(Long id);

    List<User> findAllUsers();

}
