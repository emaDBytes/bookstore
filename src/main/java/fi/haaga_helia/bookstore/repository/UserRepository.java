package fi.haaga_helia.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import fi.haaga_helia.bookstore.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}