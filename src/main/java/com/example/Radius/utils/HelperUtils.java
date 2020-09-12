package com.example.Radius.utils;

import java.math.BigDecimal;

public class HelperUtils {
    public static Double getPercent(Double value, Double percent) {
        return (value * percent) / 100;
    }

    public static boolean isValueInPlusPercent(Double percent, BigDecimal value, BigDecimal target) {
        BigDecimal percentageAmount = value.multiply(BigDecimal.valueOf(percent / 100));
        BigDecimal totalAmount = value.add(percentageAmount);
        return totalAmount.compareTo(target) >= 0;
    }

    public static boolean isValueInMinusPercent(Double percent, BigDecimal value, BigDecimal target) {
        BigDecimal percentageAmount = value.multiply(BigDecimal.valueOf(percent / 100));
        BigDecimal totalAmount = value.subtract(percentageAmount);
        return totalAmount.compareTo(target) <= 0;
    }

}
