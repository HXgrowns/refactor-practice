package com.twu.refactoring;

import java.util.Arrays;
import java.util.function.IntPredicate;

public class NumberCruncher {
    private final int[] numbers;

    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
    }

    public int countEven() {
        return count(number -> number % 2 == 0);
    }

    public int countOdd() {
        return count(number -> number % 2 == 1);
    }

    public int countPositive() {
        return count(number -> number >= 0);
    }

    public int countNegative() {
        return count(number -> number < 0);
    }

    private int count(IntPredicate intPredicate) {
        return (int) Arrays.stream(numbers).filter(intPredicate).count();
    }
}
