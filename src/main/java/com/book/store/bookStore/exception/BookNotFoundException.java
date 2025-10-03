package com.book.store.bookStore.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id) {
        super("Book with id " + id + " not found");
    }
}
