package ma.emsi.springbootinit.repositories;

import ma.emsi.springbootinit.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo
        extends JpaRepository<Product,Long> {
    Product findByName(String name);
}
