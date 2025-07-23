package valeriapagliarini.u5d8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valeriapagliarini.u5d8.entities.Blog;

public interface BlogsRepository extends JpaRepository<Blog, Long> {
}
