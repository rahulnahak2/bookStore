package com.book.store.bookStore.services;


import com.book.store.bookStore.entity.BookEntity;
import com.book.store.bookStore.exception.BookNotFoundException;
import com.book.store.bookStore.exception.DuplicateBookException;
import com.book.store.bookStore.model.Book;
import com.book.store.bookStore.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class BookServiceTest {
    @Mock
    private BookRepository bookRepo;

    @InjectMocks
    private BookService bookService;

    private BookEntity bookEntity;
    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookEntity = new BookEntity(1L, "1984", "George Orwell", 12.5, 10, null);
        book = new Book(null, "1984", "George Orwell", 12.5, 10);
    }

    @Test
    public void testAddBook_Success() {
        when(bookRepo.findByTitleAndAuthor(anyString(), anyString())).thenReturn(Optional.empty());
        when(bookRepo.save(any(BookEntity.class))).thenReturn(bookEntity);

        Book result = bookService.addBook(book);
        assertEquals("1984", result.getTitle());
        verify(bookRepo, times(1)).save(any(BookEntity.class));
    }

    @Test
    public void testAddBook_Duplicate() {
        when(bookRepo.findByTitleAndAuthor(anyString(), anyString())).thenReturn(Optional.of(bookEntity));

        assertThrows(DuplicateBookException.class, () -> bookService.addBook(book));
    }

    @Test
    public void testUpdateBook_Success() {
        when(bookRepo.findById(1L)).thenReturn(Optional.of(bookEntity));
        when(bookRepo.findByTitleAndAuthor(anyString(), anyString())).thenReturn(Optional.empty());
        when(bookRepo.save(any(BookEntity.class))).thenReturn(bookEntity);

        Book updated = bookService.updateBook(1L, book);
        assertEquals("1984", updated.getTitle());
    }

    @Test
    public void testUpdateBook_NotFound() {
        when(bookRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> bookService.updateBook(1L, book));
    }

    @Test
    public void testDeleteBook_Success() {
        when(bookRepo.findById(1L)).thenReturn(Optional.of(bookEntity));
        doNothing().when(bookRepo).delete(bookEntity);

        String msg = bookService.deleteBook(1L);
        assertTrue(msg.contains("deleted successfully"));
    }

    @Test
    public void testDeleteBook_NotFound() {
        when(bookRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(1L));
    }
}
