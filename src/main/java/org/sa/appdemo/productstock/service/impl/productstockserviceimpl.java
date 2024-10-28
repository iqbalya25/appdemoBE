//package org.sa.appdemo.productstock.service.impl;
//
//import lombok.RequiredArgsConstructor;
//import org.sa.appdemo.product.entity.Product;
//
//import org.sa.appdemo.users.entity.User;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class StockService {
//    private final ProductStockRepository productStockRepository;
//    private final StockMovementRepository stockMovementRepository;
//
//    @Transactional
//    public void updateStock(Product product, int quantity, String movementType,
//                            String referenceType, Long referenceId, User user) {
//        // Get or create product stock
//        ProductStock stock = productStockRepository.findByProductId(product.getId());
//        if (stock == null) {
//            stock = new ProductStock();
//            stock.setProduct(product);
//            stock.setQuantity(0);
//        }
//
//        // Update stock quantity
//        if ("IN".equals(movementType)) {
//            stock.setQuantity(stock.getQuantity() + quantity);
//        } else if ("OUT".equals(movementType)) {
//            int newQuantity = stock.getQuantity() - quantity;
//            if (newQuantity < 0) {
//                throw new RuntimeException("Insufficient stock");
//            }
//            stock.setQuantity(newQuantity);
//        }
//
//        productStockRepository.save(stock);
//
//        // Record stock movement
//        StockMovement movement = new StockMovement();
//        movement.setProduct(product);
//        movement.setQuantity(quantity);
//        movement.setMovementType(movementType);
//        movement.setReferenceType(referenceType);
//        movement.setReferenceId(referenceId);
//        movement.setCreatedBy(user);
//
//        stockMovementRepository.save(movement);
//    }
//}