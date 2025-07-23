package valeriapagliarini.u5d8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import valeriapagliarini.u5d8.entities.Author;

import java.util.Optional;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByEmail(String email);
}
