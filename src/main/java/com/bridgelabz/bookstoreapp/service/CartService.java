package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.exceptions.CustomException;
import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.model.Cart;
import com.bridgelabz.bookstoreapp.model.User;
import com.bridgelabz.bookstoreapp.repository.CartRepository;
import com.bridgelabz.bookstoreapp.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    public CartRepository cartRepository;
    @Autowired
    public UserService userService;
    @Autowired
    public BookService bookService;
    @Autowired
    TokenUtility tokenUtility;
    public double calculateTotalPrice(int qty, double price){
        return qty*price;
    }
    @Override
    public List<Cart> getAllItems() {
        List<Cart> cartList =  cartRepository.findAll();
        if (cartList.isEmpty())
            throw new CustomException("Cart List is empty!");
        return cartList;
    }
    @Override
    public Cart addItem(String token, CartDTO cartDTO) {
        int userId = tokenUtility.decodeToken(token);
        Book book = bookService.getBookById(cartDTO.getBookId());
        User user = userService.getUserById(token);
        Integer existingDataId = cartRepository.getExistingItemOfCart(cartDTO.getBookId(), userId);
        if(existingDataId == null) {
            if (cartDTO.quantity <= book.getQuantity()) {
                double total_price = calculateTotalPrice(cartDTO.quantity, book.getPrice());
                Cart cart = new Cart(user, book, cartDTO, total_price);
                return cartRepository.save(cart);
            } else throw new CustomException("Book quantity is not enough!");
        }
        else {
            Cart updatedCart = this.updateBookQuantityInCart(existingDataId, cartDTO);
            return updatedCart;
        }
    }
    @Override
    public Cart getCartItemById(int id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new CustomException("Cart with id "+id+" not found!"));
    }
    @Override
    public Cart editCart(int id, CartDTO cartDTO) {
        Cart cart = this.getCartItemById(id);
        Book book = bookService.getBookById(cartDTO.getBookId());
        if (cartDTO.quantity <= book.getQuantity()) {
            double total_price = calculateTotalPrice(cartDTO.quantity, book.getPrice());
            cart.updateCart(book, cartDTO, total_price);
            return cartRepository.save(cart);
        }
        else throw new CustomException("Book quantity is not enough!");
    }
    @Override
    public void deleteItem(int id) {
        Cart cart = this.getCartItemById(id);
        cartRepository.delete(cart);
    }
    @Override
    public Cart updateBookQuantityInCart(int id, CartDTO cartDTO) {
        Cart cart = this.getCartItemById(id);
        Book book = cart.getBookData();
        if (cartDTO.quantity <= book.getQuantity()) {
            cart.setQuantity(cartDTO.quantity);
            cart.setTotalPrice(calculateTotalPrice(cartDTO.quantity, book.getPrice()));
            return cartRepository.save(cart);
        }
        else throw new CustomException("Book quantity is not enough!");
    }
    @Override
    public List<Cart> getCartItemByUserId(String token) {
        int id = tokenUtility.decodeToken(token);
        List<Cart> cartList = cartRepository.getCartsByUserId(id);
        if (cartList.isEmpty())
            throw new CustomException("Cart with User token "+token+" not found!");
        return cartList;
    }
}
