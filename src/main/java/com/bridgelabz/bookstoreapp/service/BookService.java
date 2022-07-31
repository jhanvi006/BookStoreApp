package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.exceptions.CustomException;
import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService implements IBookService {
    @Autowired
    private BookRepository repository;
    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = repository.findAll();
        if(bookList.isEmpty())
            throw new CustomException("Book List is empty!");
        return bookList;
    }
    @Override
    public Book addNewBook(BookDTO bookDTO) {
        Book bookExists = repository.findByBookName(bookDTO.bookName);
        if(bookExists != null)
            throw new CustomException(bookDTO.bookName+" Book already exists.");
        else {
            Book book = new Book(bookDTO);
            log.info("New book added!");
            return repository.save(book);
        }
    }
    @Override
    public Book getBookById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException("Book with id "+id+" not found!"));
    }
    @Override
    public Book editBook(int id, BookDTO bookDTO) {
        Book book = this.getBookById(id);
        book.updateBookDetails(bookDTO);
        log.info("Book info updated for id "+id+" !");
        return repository.save(book);
    }
    @Override
    public void deleteBook(int id) {
        Book book = this.getBookById(id);
        repository.delete(book);
        log.info("Book info deleted for id "+id+" !");
    }
    @Override
    public Book getBookByName(String bookName) {
        Book book = repository.findByBookName(bookName);
        if(book != null)
            return book;
        else
             throw new CustomException("Book with name "+bookName+" not found!");
    }
    @Override
    public Book updateBookQuantity(int id, BookDTO bookDTO) {
        Book book = this.getBookById(id);
        book.setQuantity(bookDTO.getQuantity());
        return repository.save(book);
    }
    @Override
    public List<Book> sortBooks() {
        List<Book> bookList = repository.findByOrderByBookName();
        if (bookList.isEmpty())
            throw new CustomException("Book List is empty!");
        return bookList;
    }
    @Override
    public List<Book> sortBooksByPrice() {
        List<Book> bookList = repository.findByOrderByPrice();
        if (bookList.isEmpty())
            throw new CustomException("Book List is empty!");
        return bookList;
    }
    @Override
    public List<Book> sortBooksByPriceDesc() {
        List<Book> bookList = repository.findByOrderByPriceDesc();
        if (bookList.isEmpty())
            throw new CustomException("Book List is empty!");
        return bookList;
    }
}
