package org.Temirjohn.Task_5.stratergy;

public class RegularPricingStrategy implements PricingStrategy{

    @Override
    public double calculate(double originalPrice) {
        return originalPrice;
    }

    @Override
    public String getDescription() {
        return "Base description";
    }
}
