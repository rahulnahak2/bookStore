package com.book.store.bookStore;

import com.book.store.bookStore.entity.BookEntity;
import com.book.store.bookStore.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(BookRepository bookRepo) {
		return args -> {
			bookRepo.save(new BookEntity("Pride and Prejudice", "Jane Austen", 14.99, 10));
			bookRepo.save(new BookEntity("1984", "George Orwell", 12.50, 8));
			bookRepo.save(new BookEntity("The Great Gatsby", "F. Scott Fitzgerald", 10.99, 5));
		};
	}


}
