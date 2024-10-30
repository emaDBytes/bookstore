package fi.haaga_helia.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import fi.haaga_helia.bookstore.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}