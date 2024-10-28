package org.sa.appdemo.productstock.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.sa.appdemo.product.entity.Product;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_stocks")
@Data
public class ProductStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "warehouse_location")
    private String warehouseLocation;

    @Column(nullable = false)
    private Integer quantity = 0;

    @Column(name = "min_stock_level", nullable = false)
    private Integer minStockLevel = 0;

    @Column(name = "max_stock_level")
    private Integer maxStockLevel;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}