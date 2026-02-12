package org.Temirjohn.Task_5.stratergy;

public class SalePricingStrategy implements PricingStrategy {

    @Override
    public double calculate(double originalPrice) {
        return originalPrice * 0.80;
    }

    @Override
    public String getDescription() {
        return "Sale 20%";
    }
}
