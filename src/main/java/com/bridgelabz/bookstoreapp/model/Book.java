package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String bookName, authorName, bookDescription, bookImage;
    private double price;
    private int quantity;

    public Book(BookDTO bookDTO){ this.updateBookDetails(bookDTO); }
    public void updateBookDetails(BookDTO bookDTO){
        this.bookName=bookDTO.bookName;
        this.authorName=bookDTO.authorName;
        this.bookDescription=bookDTO.bookDescription;
        this.bookImage=bookDTO.bookImage;
        this.price=bookDTO.price;
        this.quantity=bookDTO.quantity;
    }
}
