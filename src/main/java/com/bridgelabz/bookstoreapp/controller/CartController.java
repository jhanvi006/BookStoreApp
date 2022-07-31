package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.Cart;
import com.bridgelabz.bookstoreapp.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CartController {
    @Autowired
    public ICartService cartService;

    @GetMapping( "/getAllCartItems")
    public ResponseEntity<ResponseDTO> getAllItems(){
        List<Cart> cartList = cartService.getAllItems();
        ResponseDTO responseDTO = new ResponseDTO("Get call successful", cartList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PostMapping("/addToCart/{token}")
    public ResponseEntity<ResponseDTO> addToCart(@PathVariable String token, @Valid @RequestBody CartDTO cartDTO){
        Cart cart = cartService.addItem(token, cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("Added to Cart successfully", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getCartById/{id}")
    public ResponseEntity<ResponseDTO> getCartById(@PathVariable int id){
        Cart cart = cartService.getCartItemById(id);
        ResponseDTO responseDTO = new ResponseDTO("Get call for id successful", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getCartByUserId/{token}")
    public ResponseEntity<ResponseDTO> getCartByUserId(@PathVariable String token){
        List<Cart> carts = cartService.getCartItemByUserId(token);
        ResponseDTO responseDTO = new ResponseDTO("Cart details for User ", carts);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PutMapping("/editCartItem/{id}")
    public ResponseEntity<ResponseDTO> editCartItem(@RequestBody CartDTO cartDTO, @PathVariable int id){
        Cart cart = cartService.editCart(id, cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("Cart updated successfully", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/deleteItemFromCart/{id}")
    public ResponseEntity<ResponseDTO> deleteCartItem(@PathVariable int id){
        cartService.deleteItem(id);
        ResponseDTO responseDTO = new ResponseDTO("Item From Cart Deleted Successfully", "Cart item deleted for id: "+id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PutMapping("/updateQtyInCart/{id}")
    public ResponseEntity<ResponseDTO> updateQuantityInCart(@PathVariable int id, @RequestBody CartDTO cartDTO){
        Cart cart = cartService.updateBookQuantityInCart(id, cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("Book Quantity updated in Cart successfully", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
