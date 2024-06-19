package com.skillbox.fibonacci;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FibonacciCalculatorTest {
    private final FibonacciCalculator calculator = new FibonacciCalculator();

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "2, 1",
            "3, 2",
            "4, 3",
            "5, 5",
            "6, 8",
            "10, 55"
    })
    void testGetFibonacciNumber(int index, int expected) {
        assertEquals(expected, calculator.getFibonacciNumber(index));
    }

    @Test
    public void testGetFibonacciNumberThrowsExceptionForInvalidIndex() {
        assertThrows(IllegalArgumentException.class, () -> calculator.getFibonacciNumber(0));
    }

    @Test
    public void testGetFibonacciNumberForFirstTwoIndices() {
        assertEquals(1, calculator.getFibonacciNumber(1));
        assertEquals(1, calculator.getFibonacciNumber(2));
    }

}
