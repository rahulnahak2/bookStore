package com.book.store.bookStore.config;

import com.book.store.bookStore.entity.BookEntity;
import com.book.store.bookStore.entity.UserEntity;
import com.book.store.bookStore.repositories.BookRepository;
import com.book.store.bookStore.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner init(UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            if (userRepo.findByUsername("admin").isEmpty()) {
                UserEntity admin = new UserEntity();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("password"));
                admin.setRole("ROLE_ADMIN");
                userRepo.save(admin);
            }
        };
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
