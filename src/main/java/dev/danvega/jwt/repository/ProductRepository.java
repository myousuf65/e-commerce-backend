package dev.danvega.jwt.repository;

import dev.danvega.jwt.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional
    List<Product> findAllByProductCategory(String category);

    @Transactional
    List<Product> findAllByUsername(String username);

    void deleteByProductName(String name);

    Product findByProductName(String name);
}
