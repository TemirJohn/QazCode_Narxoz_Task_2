package org.Temirjohn.Task_5.repository;

import org.Temirjohn.Task_5.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryProductRepository implements ProductRepository {

    private final Map<Long, Product> storage = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(seq.getAndIncrement());
        }
        storage.put(product.getId(), product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Product> findByID(Long id) {
        return Optional.ofNullable(storage.get(id));
    }
}
