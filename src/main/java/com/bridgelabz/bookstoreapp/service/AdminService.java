package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.AdminDTO;
import com.bridgelabz.bookstoreapp.dto.LoginDTO;
import com.bridgelabz.bookstoreapp.exceptions.CustomException;
import com.bridgelabz.bookstoreapp.model.Admin;
import com.bridgelabz.bookstoreapp.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminService implements IAdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin login(LoginDTO loginDTO) {
        Admin admin = adminRepository.getAdminByEmail(loginDTO.email);
        if (admin != null) {
            admin = adminRepository.loginAdmin(loginDTO.email, loginDTO.password);
            return admin;
        }
        else throw new CustomException("Email is incorrect!");
    }

    @Override
    public Admin resetPassword(String email, AdminDTO adminDTO) {
        Admin admin = adminRepository.getAdminByEmail(email);
        if (admin != null){
            admin.setPassword(adminDTO.password);
            return adminRepository.save(admin);
        }
        else throw new CustomException("Email is incorrect!");
    }
}
