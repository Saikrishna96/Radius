package com.example.Radius.utils;

import java.math.BigDecimal;

public class HelperUtils {
    public static boolean isValueInPlusPercent(Double percent, BigDecimal value, BigDecimal target) {
        BigDecimal percentageAmount = value.multiply(BigDecimal.valueOf(percent / 100));
        BigDecimal rightLimitAmount = value.add(percentageAmount);
        //return true if target if b/w value and rightLimitAmount
        return value.compareTo(target) <= 0 && rightLimitAmount.compareTo(target) >= 0;
    }

    public static boolean isValueInMinusPercent(Double percent, BigDecimal value, BigDecimal target) {
        BigDecimal percentageAmount = value.multiply(BigDecimal.valueOf(percent / 100));
        BigDecimal leftLimitAmount = value.subtract(percentageAmount);
        //return true if target if b/w leftLimit and value
        return leftLimitAmount.compareTo(target) <= 0 && value.compareTo(target) >= 0;
    }

    public static boolean isValueInPlusLimit(int limit, int value, int target) {
        int rightLimit = value + limit;
        //return true if target if b/w value and rightLimit
        return value <= target && target <= rightLimit;
    }

    public static boolean isValueInMinusLimit(int limit, int value, int target) {
        int leftLimit = value - limit;
        //return true if target if b/w leftLimit and value
        return value >= target && target >= leftLimit;
    }

}
