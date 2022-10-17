package com.bridgelabz.bookstoreapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@ToString
public class AdminDTO {
    @Email(message = "Email is invalid")
    public String email;
    @NotBlank(message = "Password should not be empty")
    public String password;
}
