package com.book.store.bookStore.controller;

import com.book.store.bookStore.model.Book;
import com.book.store.bookStore.model.Order;
import com.book.store.bookStore.services.interfaces.IBookService;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Validated
public class UserController {
    private final IBookService bookService;

    public UserController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> listBooks() {
        return ResponseEntity.ok(bookService.listBooks());
    }

    @PostMapping("/{bookId}/buy")
    public ResponseEntity<Order> buyBook(@PathVariable Long bookId,
                                         @RequestParam @Min(value = 1, message = "Quantity must be at least 1") int quantity) {
        return ResponseEntity.ok(bookService.buyBook(bookId, quantity));
    }

}
