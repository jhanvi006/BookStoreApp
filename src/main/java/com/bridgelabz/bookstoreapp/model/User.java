package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    String userName, email, password, address, token;
    boolean verified=false;

    public User(UserDTO userDTO){ this.updateUser(userDTO); }
    public void updateUser(UserDTO userDTO){
        this.userName= userDTO.userName;
        this.email=userDTO.email;
        this.password=userDTO.password;
        this.address= userDTO.address;
    }
}
