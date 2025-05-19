package sn.isi.productservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.isi.productservice.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByDesignationContainingIgnoreCase(String keyword);
    List<Product> findByCategorieId(int categoryId);
}

