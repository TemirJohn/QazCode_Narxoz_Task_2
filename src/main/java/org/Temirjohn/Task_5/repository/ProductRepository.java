package org.Temirjohn.Task_5.repository;

import org.Temirjohn.Task_5.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findByID(Long id);
}
