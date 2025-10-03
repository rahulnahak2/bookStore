package com.book.store.bookStore.services;

import com.book.store.bookStore.entity.BookEntity;
import com.book.store.bookStore.entity.OrderEntity;
import com.book.store.bookStore.exception.BookNotFoundException;
import com.book.store.bookStore.exception.OutOfStockException;
import com.book.store.bookStore.model.Book;
import com.book.store.bookStore.model.Order;
import com.book.store.bookStore.repositories.BookRepository;
import com.book.store.bookStore.repositories.OrderRepository;
import com.book.store.bookStore.util.mapper.BookMapper;
import com.book.store.bookStore.util.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepo;

    @Autowired
    OrderRepository orderRepo;


    public List<Book> listBooks() {
        return bookRepo.findAll().stream()
                .map(BookMapper::toDTO)
                .toList();
    }

    public Order buyBook(Long bookId, int quantity) {
        BookEntity book = bookRepo.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        if (book.getStock() < quantity) {
            throw new OutOfStockException(book.getTitle(), quantity, book.getStock());
        }

        book.setStock(book.getStock() - quantity);
        bookRepo.save(book);

        OrderEntity order = new OrderEntity(quantity, book);
        orderRepo.save(order);

        return OrderMapper.toDTO(order);
    }
}
