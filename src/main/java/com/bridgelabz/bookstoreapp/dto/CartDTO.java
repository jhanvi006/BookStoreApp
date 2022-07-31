package com.bridgelabz.bookstoreapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@ToString
public class CartDTO {
    @Min(value = 1, message = "quantity should be at least 1")
    public int quantity;
//    @NotNull(message = "User id should not be empty")
//    public int userId;
    @NotNull(message = "Book id should not be empty")
    public int bookId;
}
