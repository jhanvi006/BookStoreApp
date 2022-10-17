package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping( "/getAllBooks")
    public ResponseEntity<ResponseDTO> getAllBooks(){
        List<Book> bookList = bookService.getAllBooks();
        ResponseDTO responseDTO = new ResponseDTO("Get call successful", bookList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PostMapping("/addBook")
    public ResponseEntity<ResponseDTO> createNewBook(@Valid @RequestBody BookDTO bookDTO){
        Book book = bookService.addNewBook(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("New Book Data successfully", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getBookById/{id}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable int id){
        Book book = bookService.getBookById(id);
        ResponseDTO responseDTO = new ResponseDTO("Get call for id successful", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PutMapping("/editBook/{id}")
    public ResponseEntity<ResponseDTO> editBook(@Valid @RequestBody BookDTO bookDTO, @PathVariable int id){
        Book book = bookService.editBook(id, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Book data updated successfully", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable int id){
        bookService.deleteBook(id);
        ResponseDTO responseDTO = new ResponseDTO("Deleted Successfully", "Book deleted for id: "+id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getBookByName/{bookName}")
    public ResponseEntity<ResponseDTO> getByBookName(@PathVariable String bookName){
        Book book = bookService.getBookByName(bookName);
        ResponseDTO responseDTO = new ResponseDTO("Get call for book name successful", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PutMapping("/updateQty/{id}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable int id, @RequestBody BookDTO bookDTO){
        Book book = bookService.updateBookQuantity(id, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Book Quantity updated successfully", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/sortByName")
    public ResponseEntity<ResponseDTO> sortByBookName(){
        List<Book> bookList = bookService.sortBooks();
        ResponseDTO responseDTO = new ResponseDTO("Books sorted successfully", bookList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/sortByPriceLowToHigh")
    public ResponseEntity<ResponseDTO> sortByPriceLowToHigh(){
        List<Book> bookList = bookService.sortBooksByPrice();
        ResponseDTO responseDTO = new ResponseDTO("Books sorted successfully", bookList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/sortByPriceHighToLow")
    public ResponseEntity<ResponseDTO> sortByPriceHighToLow(){
        List<Book> bookList = bookService.sortBooksByPriceDesc();
        ResponseDTO responseDTO = new ResponseDTO("Books sorted successfully", bookList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
