package ma.emsi.springbootinit.repositories;

import ma.emsi.springbootinit.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
