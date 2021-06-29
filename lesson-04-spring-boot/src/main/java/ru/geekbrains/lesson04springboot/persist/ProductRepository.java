package ru.geekbrains.lesson04springboot.persist;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    public void addProduct(Product product) {
        if (product.getId() == null) {
            product.setId(identity.incrementAndGet());
        }
        productMap.put(product.getId(), product);
    }

    @PostConstruct
    public void initRepostory() {
        this.addProduct(new Product("Товар1", 12.4f));
        this.addProduct(new Product("Товар2", 12.4f));
        this.addProduct(new Product("Товар3", 12.4f));
    }

    public Product getProductById(Long id) {
        return productMap.get(id);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    public void deleteProductById(Long id) {
        productMap.remove(id);
    }

}
