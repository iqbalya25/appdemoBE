package org.sa.appdemo.productstock.repository;

import org.sa.appdemo.productstock.entity.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
    ProductStock findByProductId(Long productId);
}