package ru.geekbrains.lesson04springboot.persist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByPriceIsGreaterThanEqual(BigDecimal minPrice);

    List<Product> findProductByPriceIsLessThanEqual(BigDecimal maxPrice);

    List<Product> findProductByPriceIsBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
