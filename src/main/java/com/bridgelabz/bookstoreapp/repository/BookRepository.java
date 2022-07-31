package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByBookName(String bookName);
    List<Book> findByOrderByBookName();
    List<Book> findByOrderByPrice();
    List<Book> findByOrderByPriceDesc();
}
