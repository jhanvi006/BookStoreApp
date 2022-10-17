package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query(value = "SELECT * FROM admin WHERE email=:email AND password=:password", nativeQuery = true)
    Admin loginAdmin(String email, String password);
    Admin getAdminByEmail(String email);
    @Query(value = "SELECT * FROM admin WHERE admin_id=:adminId", nativeQuery = true)
    Admin getAdminById(int adminId);
}
