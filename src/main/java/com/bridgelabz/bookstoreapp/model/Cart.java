package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.hibernate.annotations.ForeignKey(name = "none")
    private int cartId;
    @OneToOne
    @JoinColumn(name = "userId")
    private User userData;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book bookData;
    private int quantity;
    private double totalPrice;
    public Cart(User userData, Book bookData, CartDTO cartDTO, double totalPrice){
        this.userData = userData;
        this.updateCart(bookData, cartDTO, totalPrice);
    }
    public void updateCart(Book bookData, CartDTO cartDTO, double totalPrice) {
        this.bookData = bookData;
        this.quantity = cartDTO.quantity;
        this.totalPrice = totalPrice;
    }
}
