package org.Temirjohn.Task_5.controller;

import org.Temirjohn.Task_5.stratergy.RegularPricingStrategy;
import org.Temirjohn.Task_5.stratergy.SalePricingStrategy;
import org.Temirjohn.Task_5.dto.ProductDisplayDto;
import org.Temirjohn.Task_5.service.ProductService;

import java.util.List;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void addProduct(String name, double price, String category) {
        productService.createProduct(name, price, category);
        System.out.println(">> add " + name);
    }

    public void showCategory() {
        System.out.println("-- CATEGORY ---");
        List<ProductDisplayDto> catalog = productService.getCategory();
        if (catalog.isEmpty()) {
            System.out.println("Category empty");
        } else {
            catalog.forEach(System.out::println);
        }
        System.out.println("-----------------------");
    }


    public void startSale() {
        System.out.println(">> WARN! SALE");
        productService.setPricingStrategy(new SalePricingStrategy());
    }

    public void endSale() {
        System.out.println(">> SALE END!");
        productService.setPricingStrategy(new RegularPricingStrategy());
    }
}
