package com.book.store.bookStore.exception;

public class OutOfStockException extends RuntimeException{
    public OutOfStockException(String title, int requested, int available) {
        super("Not enough stock for book '" + title +
                "'. Requested: " + requested + ", Available: " + available);
    }
}
