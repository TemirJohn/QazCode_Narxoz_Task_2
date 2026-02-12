package org.Temirjohn.Task_5.service;


import org.Temirjohn.Task_5.stratergy.PricingStrategy;
import org.Temirjohn.Task_5.stratergy.RegularPricingStrategy;
import org.Temirjohn.Task_5.dto.ProductDisplayDto;
import org.Temirjohn.Task_5.entity.Product;
import org.Temirjohn.Task_5.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private final ProductRepository repository;
    private PricingStrategy pricingStrategy;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
        this.pricingStrategy = new RegularPricingStrategy();
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
        System.out.println("[LOG] Strategy changed on " + pricingStrategy.getDescription());
    }

    public void createProduct(String name, double price, String category) {
        Product product = new Product(name, price, category);
        repository.save(product);
    }

    public List<ProductDisplayDto> getCategory() {
        return repository.findAll().stream()
                .map(p -> {
                    double newPrice = pricingStrategy.calculate(p.getOriginPrice());
                    return new ProductDisplayDto(p.getName(), newPrice, pricingStrategy.getDescription());
                })
                .collect(Collectors.toList());
    }

    public Product getProductDetail(Long id) {
        return repository.findByID(id).orElseThrow();
    }


}
