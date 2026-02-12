package org.Temirjohn.Task_5.entity;

public class Product {
    private Long id;
    private String name;
    private double originPrice;
    private  String category;

    public Product(String name, double originPrice, String category) {
        this.id = id;
        this.name = name;
        this.originPrice = originPrice;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", originPrice=" + originPrice +
                ", category='" + category + '\'' +
                '}';
    }
}
