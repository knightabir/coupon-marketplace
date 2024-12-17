/**
 * This interface contains all the methods for the User Service.
 * It is responsible for handling all the operations related to the User entity.
 *
 * @author Abir Sarkar
 */
package com.marketplace.couponMarketplace.service;

import com.marketplace.couponMarketplace.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * This method registers a user.
     * It takes a User object, saves it to the database and returns the saved user.
     * @param user the User to be registered
     * @return the saved User
     */
    User registerUser(User user);
    /**
     * This method returns a user by email.
     * It takes an email as a parameter, searches the database, and returns the user associated with the email.
     * @param email the email of the User
     * @return the User associated with the email
     */
    Optional<User> getUserByEmail(String email);
    /**
     * This method returns a user by id.
     * It takes an id as a parameter, searches the database, and returns the user associated with the id.
     * @param id the id of the User
     * @return the User associated with the id
     */
    User getUserById(String id);
    /**
     * This method deletes a user by id.
     * It takes an id as a parameter, searches the database, and deletes the user associated with the id.
     * @param id the id of the User to be deleted
     */
    void deleteUser(String id);
    /**
     * This method updates a user by id.
     * It takes an id and a User object as parameters, searches the database, and updates the user associated with the id.
     * @param id the id of the User to be updated
     * @param user the updated User
     * @return the updated User
     */

    List<User> getAllUsers();
    User updateUser(String id,User user);
}