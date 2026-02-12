package org.Temirjohn.Task_5;

import org.Temirjohn.Task_5.controller.ProductController;
import org.Temirjohn.Task_5.repository.InMemoryProductRepository;
import org.Temirjohn.Task_5.repository.ProductRepository;
import org.Temirjohn.Task_5.service.ProductService;

public class Main {
    public static void main(String[] args) {
        ProductRepository repository = new InMemoryProductRepository();
        ProductService service = new ProductService(repository);
        ProductController controller = new ProductController(service);

        controller.addProduct("Laptop", 1000.0, "Electronics");
        controller.addProduct("IPhone", 500.0, "Electronics");
        controller.addProduct("Book", 40.0, "Books");

        controller.showCategory();
        controller.startSale();
        controller.showCategory();

        controller.endSale();
        controller.showCategory();
    }
}
