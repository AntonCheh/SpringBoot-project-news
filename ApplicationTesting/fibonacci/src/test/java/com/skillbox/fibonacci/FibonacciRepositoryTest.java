package com.skillbox.fibonacci;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FibonacciRepositoryTest extends PostgresTestContainerInitializer {

    @Autowired
    private FibonacciRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Test
    void testSaveAndFindFibonacciNumber() {
        FibonacciNumber number = new FibonacciNumber(1, 1);
        repository.save(number);
        entityManager.flush();
        entityManager.detach(number);

        List<FibonacciNumber> actual = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number WHERE index = 1",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"), rs.getInt("value"))
        );

        assertEquals(1, actual.size());
        assertEquals(number.getIndex(), actual.get(0).getIndex());
        assertEquals(number.getValue(), actual.get(0).getValue());
    }

    @Test
    void testFindByIndex() {
        FibonacciNumber number = new FibonacciNumber(1, 1);
        repository.save(number);
        entityManager.flush();
        entityManager.detach(number);

        Optional<FibonacciNumber> foundNumber = repository.findByIndex(1);

        assertTrue(foundNumber.isPresent());
        assertEquals(number.getIndex(), foundNumber.get().getIndex());
        assertEquals(number.getValue(), foundNumber.get().getValue());
    }

    @Test
    void testInsertOnConflict() {
        FibonacciNumber number1 = new FibonacciNumber(1, 1);
        repository.save(number1);
        entityManager.flush();
        entityManager.detach(number1);

        FibonacciNumber number2 = new FibonacciNumber(1, 2);
        repository.save(number2);
        entityManager.flush();
        entityManager.detach(number2);

        List<FibonacciNumber> actual = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number WHERE index = 1",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"), rs.getInt("value"))
        );

        assertEquals(1, actual.size());
        assertEquals(number2.getValue(), actual.get(0).getValue());
    }

}
