package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.LoginDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.model.User;
import com.bridgelabz.bookstoreapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<ResponseDTO> getUserData(){
        List<User> userList = userService.getAllUsers();
        ResponseDTO responseDTO = new ResponseDTO("Get call successful", userList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PostMapping("/addUser")
    public ResponseEntity<ResponseDTO> createNewUser(@Valid @RequestBody UserDTO userDTO){
        User user = userService.registerUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("User registered successfully", user);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/verify/{token}")
    public ResponseEntity<ResponseDTO> verifyUser(@PathVariable String token){
        ResponseDTO responseDTO = new ResponseDTO("User verified successfully", userService.verifyUser(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getUserById/{token}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable String token){
        User user = userService.getUserById(token);
        ResponseDTO responseDTO = new ResponseDTO("Get call for id successful", user);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PutMapping("/editUser/{token}")
    public ResponseEntity<ResponseDTO> editUser(@Valid @RequestBody UserDTO userDTO, @PathVariable String token){
        User user = userService.editUser(token, userDTO);
        ResponseDTO responseDTO = new ResponseDTO("User data updated successfully", user);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/deleteUser/{token}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String token){
        userService.deleteUser(token);
        ResponseDTO responseDTO = new ResponseDTO("Deleted Successfully", "User deleted!");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> userLogin(@RequestBody LoginDTO loginDTO){
        User user = userService.login(loginDTO);
        if (user != null){
            ResponseDTO responseDTO = new ResponseDTO("Login Successful", user);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        else {
            ResponseDTO responseDTO = new ResponseDTO("Login Failed!", "Email or password is incorrect!");
            return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
        }
    }
    @PutMapping("/forgetPassword")
    public ResponseEntity<ResponseDTO> forgetPassword(@RequestParam String email, @RequestBody UserDTO userDTO){
        User user = userService.resetPassword(email, userDTO);
        ResponseDTO responseDTO = new ResponseDTO("Password changed successfully", user);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
