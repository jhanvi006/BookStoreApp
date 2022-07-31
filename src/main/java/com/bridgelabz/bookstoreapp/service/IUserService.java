package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.LoginDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User registerUser(UserDTO userDTO);
    User getUserById(String token);
    User editUser(String token, UserDTO userDTO);
    void deleteUser(String token);
    User login(LoginDTO loginDTO);
    User resetPassword(String email, UserDTO userDTO);
    User verifyUser(String token);
}
