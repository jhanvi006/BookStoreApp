package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAllOrders();
    Order placeOrder(String token, OrderDTO orderDTO);
    Order getOrderItemById(int id);
    void cancelOrder(int id);
    List<Order> getOrderItemByUserId(String token);
    void updateOrder(int id);
    List<Order> getPendingOrders();
}
