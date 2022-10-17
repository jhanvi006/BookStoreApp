package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE order_details SET cancel=true WHERE order_id=:id", nativeQuery = true)
    void updateCancel(int id);

    @Query(value = "SELECT * FROM order_details WHERE user_id=:id", nativeQuery = true)
    List<Order> findAllByUserId(int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE order_details SET order_status='COMPLETED' WHERE order_id=:id", nativeQuery = true)
    void updateOrderStatus(int id);

    @Query(value = "SELECT * FROM order_details WHERE order_status='PENDING'", nativeQuery = true)
    List<Order> findPendingOrders();
}
