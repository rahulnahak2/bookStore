package com.book.store.bookStore.repositories;

import com.book.store.bookStore.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
