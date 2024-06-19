package com.skillbox.fibonacci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FibonacciServiceTest {
    @Mock
    private FibonacciRepository repository;

    @Mock
    private FibonacciCalculator calculator;

    @InjectMocks
    private FibonacciService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFibonacciNumberFromDatabase() {
        FibonacciNumber number = new FibonacciNumber(5, 5);
        when(repository.findByIndex(5)).thenReturn(Optional.of(number));

        FibonacciNumber result = service.fibonacciNumber(5);

        assertEquals(5, result.getValue());
        verify(repository, times(1)).findByIndex(5);
        verify(calculator, times(0)).getFibonacciNumber(5);
    }

    @Test
    void testFibonacciNumberComputedAndSaved() {
        when(repository.findByIndex(5)).thenReturn(Optional.empty());
        when(calculator.getFibonacciNumber(5)).thenReturn(5);

        FibonacciNumber result = service.fibonacciNumber(5);

        assertEquals(5, result.getValue());
        verify(repository, times(1)).findByIndex(5);
        verify(calculator, times(1)).getFibonacciNumber(5);
        verify(repository, times(1)).save(any(FibonacciNumber.class));
    }

    @Test
    void testFibonacciNumberThrowsExceptionForInvalidIndex() {
        assertThrows(IllegalArgumentException.class, () -> service.fibonacciNumber(0));
    }
}
