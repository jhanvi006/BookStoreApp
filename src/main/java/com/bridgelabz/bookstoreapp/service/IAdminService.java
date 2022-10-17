package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.AdminDTO;
import com.bridgelabz.bookstoreapp.dto.LoginDTO;
import com.bridgelabz.bookstoreapp.model.Admin;

public interface IAdminService {
    Admin login(LoginDTO loginDTO);
    Admin resetPassword(String email, AdminDTO adminDTO);
}
