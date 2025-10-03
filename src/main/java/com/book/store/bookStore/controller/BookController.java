package com.book.store.bookStore.controller;

import com.book.store.bookStore.model.Book;
import com.book.store.bookStore.model.Order;
import com.book.store.bookStore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping
    public List<Book> listBooks() {
        return bookService.listBooks();
    }

    @PostMapping("/{bookId}/buy")
    public Order buyBook(@PathVariable Long bookId, @RequestParam int quantity) {
        return bookService.buyBook(bookId, quantity);
    }

}
