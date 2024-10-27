package org.sa.appdemo.product.dto;

import lombok.Data;
import org.sa.appdemo.category.dto.CategoryDTO;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long categoryId;
    private CategoryDTO category;  // Add this field
    private String barcode;
    private String imageUrl;
    private boolean isActive;
}