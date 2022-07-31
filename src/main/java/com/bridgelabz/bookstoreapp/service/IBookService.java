package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();
    Book addNewBook(BookDTO bookDTO);
    Book getBookById(int id);
    Book editBook(int id, BookDTO bookDTO);
    void deleteBook(int id);
    Book getBookByName(String bookName);
    Book updateBookQuantity(int id, BookDTO bookDTO);
    List<Book> sortBooks();
    List<Book> sortBooksByPrice();
    List<Book> sortBooksByPriceDesc();
}
