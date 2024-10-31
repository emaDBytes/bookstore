package fi.haaga_helia.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import fi.haaga_helia.bookstore.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}