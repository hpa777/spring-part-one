package ru.geekbrains;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class ProductRepository {

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    public void addProduct(Product product) {
        if (product.getId() == null) {
            product.setId(identity.incrementAndGet());
        }
        productMap.put(product.getId(), product);
    }

    public Product getProductById(Long id) {
        return productMap.get(id);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    public Product deleteProductById(Long id) {
        return productMap.remove(id);
    }


}
