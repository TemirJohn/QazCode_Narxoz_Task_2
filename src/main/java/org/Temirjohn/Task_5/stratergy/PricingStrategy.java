package org.Temirjohn.Task_5.stratergy;

public interface PricingStrategy {
    double calculate(double originalPrice);
    String getDescription();
}
