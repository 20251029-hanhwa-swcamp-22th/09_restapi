package org.ho.cqrs.product.command.infrastructure.repository;

import org.ho.cqrs.product.command.domain.aggregate.Product;
import org.ho.cqrs.product.command.domain.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository
    extends JpaRepository<Product,Long>, ProductRepository {


}
