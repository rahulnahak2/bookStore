package com.book.store.bookStore.model;

public class Book {
    private Long id;
    private String title;
    private String author;
    private double price;
    private int stock;

    public Book(Long id, String title, double price, String author, int stock) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.author = author;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
