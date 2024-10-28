package org.sa.appdemo.stockmovement.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.sa.appdemo.product.entity.Product;
import org.sa.appdemo.users.entity.User;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_movements")
@Data
public class StockMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "movement_type", nullable = false)
    private String movementType; // IN, OUT, ADJUSTMENT

    @Column(name = "reference_type")
    private String referenceType; // ORDER, RESTOCK, INVENTORY_ADJUSTMENT

    @Column(name = "reference_id")
    private Long referenceId;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}