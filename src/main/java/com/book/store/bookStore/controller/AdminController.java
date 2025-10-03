package com.book.store.bookStore.controller;

import com.book.store.bookStore.model.SuccessResponse;
import com.book.store.bookStore.services.interfaces.IBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.book.store.bookStore.model.Book;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/books")
public class AdminController {
    private final IBookService bookService;

    public AdminController(IBookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book created = bookService.addBook(book);
        return ResponseEntity.ok(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteBook(@PathVariable Long id) {
        String message = bookService.deleteBook(id);
        return ResponseEntity.ok(new SuccessResponse("success", message));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }
}
