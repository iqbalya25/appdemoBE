package org.sa.appdemo.category.repository;


import org.sa.appdemo.category.entity.Category;
import org.sa.appdemo.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Product> findByBarcode(String barcode);
}