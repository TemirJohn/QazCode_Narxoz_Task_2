package org.Temirjohn.Task_5.dto;

public class ProductDisplayDto {
    private String name;
    private double finalPrice;
    private String priceNote;

    public ProductDisplayDto(String name, double finalPrice, String priceNote) {
        this.name = name;
        this.finalPrice = finalPrice;
        this.priceNote = priceNote;
    }

    @Override
    public String toString() {
        return "ProductDisplayDto{" +
                "name='" + name + '\'' +
                ", finalPrice=" + finalPrice +
                ", priceNote='" + priceNote + '\'' +
                '}';
    }
}
