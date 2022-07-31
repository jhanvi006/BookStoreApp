package com.bridgelabz.bookstoreapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BookStoreAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookStoreAppApplication.class, args);
        log.info("Welcome to Book Store App!");
    }
}
