package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.Order;
import com.bridgelabz.bookstoreapp.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    public IOrderService orderService;
    @GetMapping( "/getOrders")
    public ResponseEntity<ResponseDTO> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        ResponseDTO responseDTO = new ResponseDTO("Get call for orders successful", orders);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PostMapping("/placeOrder/{token}")
    public ResponseEntity<ResponseDTO> placeOrder(@PathVariable String token, @Valid @RequestBody OrderDTO orderDTO){
        Order order = orderService.placeOrder(token, orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("Order placed successfully", order);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<ResponseDTO> getOrderById(@PathVariable int id){
        Order order = orderService.getOrderItemById(id);
        ResponseDTO responseDTO = new ResponseDTO("Order details for order id "+id+" successful", order);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getOrderByUserId/{token}")
    public ResponseEntity<ResponseDTO> getOrderById(@PathVariable String token){
        List<Order> order = orderService.getOrderItemByUserId(token);
        ResponseDTO responseDTO = new ResponseDTO("Order details for user successful", order);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PutMapping("cancelOrder/{id}")
    public ResponseEntity<ResponseDTO> cancelOrder(@PathVariable int id){
        orderService.cancelOrder(id);
        ResponseDTO responseDTO = new ResponseDTO("Order cancel for id successful", "Order cancelled for id: "+id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
