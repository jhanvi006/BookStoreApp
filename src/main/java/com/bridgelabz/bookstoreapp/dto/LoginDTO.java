package com.bridgelabz.bookstoreapp.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class LoginDTO {
    public String email, password;
}
