package com.book.store.bookStore.exception;

public class DuplicateBookException extends RuntimeException {
    public DuplicateBookException(String title, String author) {
        super("Book '" + title + "' by '" + author + "' already exists");
    }
}
