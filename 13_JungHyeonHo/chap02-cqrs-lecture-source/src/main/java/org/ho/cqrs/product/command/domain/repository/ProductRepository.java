package org.ho.cqrs.product.command.domain.repository;

import org.ho.cqrs.product.command.domain.aggregate.Product;

import java.util.Optional;

public interface ProductRepository {

  Product save(Product product);

  Optional<Product> findById(Long productCode);

  void deleteById(Long productCode);
}
