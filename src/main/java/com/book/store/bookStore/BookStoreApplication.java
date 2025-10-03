package com.book.store.bookStore;

import com.book.store.bookStore.entity.BookEntity;
import com.book.store.bookStore.entity.UserEntity;
import com.book.store.bookStore.repositories.BookRepository;
import com.book.store.bookStore.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

}
