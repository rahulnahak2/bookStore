package com.book.store.bookStore.services.interfaces;

import com.book.store.bookStore.model.Book;
import com.book.store.bookStore.model.Order;

import java.util.List;

public interface IBookService {
    List<Book> listBooks();
    Order buyBook(Long id, int quantity);
    Book addBook(Book book);
    Book updateBook(Long id, Book book);
    String deleteBook(Long id);
}
