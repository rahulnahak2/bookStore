package com.book.store.bookStore.services;

import com.book.store.bookStore.entity.BookEntity;
import com.book.store.bookStore.entity.OrderEntity;
import com.book.store.bookStore.exception.BookNotFoundException;
import com.book.store.bookStore.exception.DuplicateBookException;
import com.book.store.bookStore.exception.OutOfStockException;
import com.book.store.bookStore.model.Book;
import com.book.store.bookStore.model.Order;
import com.book.store.bookStore.repositories.BookRepository;
import com.book.store.bookStore.repositories.OrderRepository;
import com.book.store.bookStore.services.interfaces.IBookService;
import com.book.store.bookStore.util.mapper.BookMapper;
import com.book.store.bookStore.util.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    private final BookRepository bookRepo;
    private final OrderRepository orderRepo;

    public BookService(BookRepository bookRepo,OrderRepository orderRepo) {
        this.bookRepo = bookRepo;
        this.orderRepo = orderRepo;
    }

    @Cacheable("books")
    public List<Book> listBooks() {
        return bookRepo.findAll().stream()
                .map(BookMapper::toDTO)
                .toList();
    }

    @Transactional
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

    @Transactional
    public Book addBook(Book dto) {
        bookRepo.findByTitleAndAuthor(dto.getTitle(), dto.getAuthor())
                .ifPresent(existing -> {
                    throw new DuplicateBookException(dto.getTitle(), dto.getAuthor());
                });

        BookEntity entity = BookMapper.toEntity(dto);
        BookEntity saved = bookRepo.save(entity);
        return BookMapper.toDTO(saved);
    }

    @Transactional
    public String deleteBook(Long id) {
        BookEntity book = bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookRepo.delete(book);
        return "Book '" + book.getTitle() + "' by '" + book.getAuthor() + "' deleted successfully";
    }

    @Transactional
    public Book updateBook(Long id, Book dto) {
        BookEntity book = bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookRepo.findByTitleAndAuthor(dto.getTitle(), dto.getAuthor())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new DuplicateBookException(dto.getTitle(), dto.getAuthor());
                });

        // Update fields
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPrice(dto.getPrice());
        book.setStock(dto.getStock());

        BookEntity updated = bookRepo.save(book);
        return BookMapper.toDTO(updated);
    }
}
