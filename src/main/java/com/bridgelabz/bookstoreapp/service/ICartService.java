package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.model.Cart;

import java.util.List;

public interface ICartService {
    List<Cart> getAllItems();
    Cart addItem(String token, CartDTO cartDTO);
    Cart getCartItemById(int id);
    Cart editCart(int id, CartDTO cartDTO);
    void deleteItem(int id);
    Cart updateBookQuantityInCart(int id, CartDTO cartDTO);
    List<Cart> getCartItemByUserId(String token);
}
