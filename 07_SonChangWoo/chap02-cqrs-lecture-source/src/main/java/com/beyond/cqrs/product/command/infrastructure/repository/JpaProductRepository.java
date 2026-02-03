package com.beyond.cqrs.product.command.infrastructure.repository;

import com.beyond.cqrs.product.command.domain.aggregate.Product;
import com.beyond.cqrs.product.command.domain.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository
    extends JpaRepository<Product, Long>, ProductRepository {


}
