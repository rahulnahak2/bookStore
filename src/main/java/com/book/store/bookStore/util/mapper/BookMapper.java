package com.book.store.bookStore.util.mapper;

import com.book.store.bookStore.entity.BookEntity;
import com.book.store.bookStore.model.Book;

public class BookMapper {
    public static Book toDTO(BookEntity entity) {
        if (entity == null) return null;
        return new Book(
                entity.getId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getPrice(),
                entity.getStock());
    }

    public static BookEntity toEntity(Book dto) {
        if (dto == null) return null;
        return new BookEntity(
                dto.getTitle(),
                dto.getAuthor(),
                dto.getPrice(),
                dto.getStock());
    }
}
