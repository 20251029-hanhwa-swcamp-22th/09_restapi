package com.ohgiraffers.cqrs.product.command.domain.repository;

import com.ohgiraffers.cqrs.product.command.domain.aggregate.Product;

import java.util.Optional;

public interface ProductRepository {
    Product save(Product newProduct);

    Optional<Product> findById(Long productCode);

    void deleteById(Long productCode);
}
