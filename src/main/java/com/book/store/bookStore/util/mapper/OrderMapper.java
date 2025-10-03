package com.book.store.bookStore.util.mapper;

import com.book.store.bookStore.entity.OrderEntity;
import com.book.store.bookStore.model.Order;

public class OrderMapper {
    public static Order toDTO(OrderEntity entity) {
        return new Order(entity.getId(),
                BookMapper.toDTO(entity.getBook()),
                entity.getQuantity());
    }
}
