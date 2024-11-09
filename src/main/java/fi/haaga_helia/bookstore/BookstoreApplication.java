package fi.haaga_helia.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.haaga_helia.bookstore.model.Book;
import fi.haaga_helia.bookstore.model.Category;
import fi.haaga_helia.bookstore.model.User;
import fi.haaga_helia.bookstore.repository.BookRepository;
import fi.haaga_helia.bookstore.repository.CategoryRepository;
import fi.haaga_helia.bookstore.repository.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository categoryRepository,
            UserRepository userRepository) {
        return (args) -> {
            // Only add books if repository is empty
            if (repository.count() == 0) {
                // Add some categories
                Category fiction = new Category("Fiction");
                Category nonFiction = new Category("Non-Fiction");
                Category science = new Category("Science");

                categoryRepository.save(fiction);
                categoryRepository.save(nonFiction);
                categoryRepository.save(science);

                // Create books with categories
                Book book1 = new Book("A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21", 29.90);
                book1.setCategory(fiction);

                Book book2 = new Book("Animal Farm", "George Orwell", 1945, "2212343-5", 24.90);
                book2.setCategory(fiction);

                Book book3 = new Book("The Hobbit", "J.R.R. Tolkien", 1937, "3454545-23", 34.90);
                book3.setCategory(fiction);

                // Save the books to the database
                repository.save(book1);
                repository.save(book2);
                repository.save(book3);
            }

            if (userRepository.count() == 0) {

                // Create BCryptPasswordEncoder
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                // Create users with BCrypt hashed passwords
                User user1 = new User("user",
                        passwordEncoder.encode("user"),
                        "user@haaga-helia.fi",
                        "USER");
                User user2 = new User("admin",
                        passwordEncoder.encode("admin"),
                        "admin@haaga-helia.fi",
                        "ADMIN");

                // Save the users to the database
                userRepository.save(user1);
                userRepository.save(user2);
            }
        };
    }
}