package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.AdminDTO;
import com.bridgelabz.bookstoreapp.dto.LoginDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.Admin;
import com.bridgelabz.bookstoreapp.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> adminLogin(@RequestBody LoginDTO loginDTO){
        Admin admin = adminService.login(loginDTO);
        if (admin != null){
            ResponseDTO responseDTO = new ResponseDTO("Login Successful", admin);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        else {
            ResponseDTO responseDTO = new ResponseDTO("Login Failed!", "Password is incorrect!");
            return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
        }
    }
    @PutMapping("/forgetPassword")
    public ResponseEntity<ResponseDTO> forgetPasswordForAdmin(@RequestParam String email, @RequestBody AdminDTO adminDTO){
        Admin admin = adminService.resetPassword(email, adminDTO);
        ResponseDTO responseDTO = new ResponseDTO("Password changed successfully", admin);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
