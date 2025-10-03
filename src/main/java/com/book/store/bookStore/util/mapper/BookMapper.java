package com.book.store.bookStore.util.mapper;

import com.book.store.bookStore.entity.BookEntity;
import com.book.store.bookStore.model.Book;

public class BookMapper {
    public static Book toDTO(BookEntity entity) {
        return new Book(entity.getId(), entity.getTitle(), entity.getPrice(), entity.getAuthor(), entity.getStock());
    }

    public static BookEntity toEntity(Book dto) {
        return new BookEntity(dto.getTitle(), dto.getAuthor(), dto.getPrice(), dto.getStock());
    }
}
