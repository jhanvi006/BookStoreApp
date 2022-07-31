package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.LoginDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.exceptions.CustomException;
import com.bridgelabz.bookstoreapp.model.Email;
import com.bridgelabz.bookstoreapp.model.User;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.util.TokenUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserService{
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenUtility tokenUtility;
    @Autowired
    private IEmailService emailService;

    public UserService() {
    }

    public void isEmpty(List<User> userList){
        if(userList.isEmpty())
            throw new CustomException("User List is empty!");
    }
    @Override
    public List<User> getAllUsers() {
        List<User> userList = repository.findAll();
        isEmpty(userList);
        return userList;
    }
    @Override
    public User registerUser(UserDTO userDTO) {
        User user = new User(userDTO);
        repository.save(user);
        String token = tokenUtility.createToken(user.getUserId());
        user.setToken(token);
        Email email = new Email(user.getEmail(), "User registered successfully", user.getUserName()+" => "+emailService.getLink(token));
        try {
            emailService.sendMail(email);
        } catch (MessagingException e) {
            throw new CustomException(e.getMessage());
        }
        log.info("New user registered!");
        return repository.save(user);
    }
    @Override
    public User getUserById(String token) {
        int id = tokenUtility.decodeToken(token);
        return repository.findById(id)
                .orElseThrow(() -> new CustomException("User with not found!"));
    }
    @Override
    public User editUser(String token, UserDTO userDTO) {
        User user = this.getUserById(token);
        user.updateUser(userDTO);
        log.info("User info updated for id "+tokenUtility.decodeToken(token)+" !");
        return repository.save(user);
    }
    @Override
    public void deleteUser(String token) {
        User user = this.getUserById(token);
        repository.delete(user);
        log.info("User info deleted for id "+tokenUtility.decodeToken(token)+" !");
    }
    @Override
    public User login(LoginDTO loginDTO) {
        User user = repository.loginUser(loginDTO.email, loginDTO.password);
        return user;
    }
    @Override
    public User resetPassword(String email, UserDTO userDTO) {
        User user = repository.getUserByEmail(email);
        if (user != null){
            user.setPassword(userDTO.password);
            return repository.save(user);
        }
        else throw new CustomException("User with email "+email+" not found!");
    }
    @Override
    public User verifyUser(String token) {
        User user = this.getUserById(token);
        user.setVerified(true);
        return repository.save(user);
    }
}
