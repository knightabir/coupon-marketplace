package com.marketplace.couponMarketplace.service.impl;

import com.marketplace.couponMarketplace.model.User;
import com.marketplace.couponMarketplace.repository.UserRepository;
import com.marketplace.couponMarketplace.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class implements the methods declared on the UserService interface.
 * This service is responsible for handling all the operations related to the User entity.
 * It uses the UserRepository to interact with the database and the PasswordEncoder to hash the passwords.
 * The service methods are annotated with logs to track the operations done on the User entity.
 */

/**
 * Author: Abir Sarkar
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        log.info("Registering a user with email: {}", user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        log.info("Getting user with email: {}", email);
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(String id) {
        log.info("Getting user with id: {}", id);
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public void deleteUser(String id) {
        log.info("Deleting user with id: {}", id);
        User existingUser = getUserById(id);
        existingUser.setDeleted(true);
        userRepository.save(existingUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(String id, User user) {
        log.info("Updating user with id: {}", id);
        User existingUser = getUserById(id);
        if (existingUser == null) {
            return null;
        }
        if (user.getFirstName() != null) {
            existingUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            existingUser.setLastName(user.getLastName());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(existingUser);
    }
}