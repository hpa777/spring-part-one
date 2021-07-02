package ru.geekbrains.entity;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductDao {

    private EntityManager entityManager;

    public ProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Product findBuId(Long id) {
        return entityManager.find(Product.class, id);
    }

    public List<Product> findAll() {
        return entityManager.createNamedQuery("allProducts", Product.class).getResultList();
    }

    public void deleteById(Long id) {
        Product product = entityManager.getReference(Product.class, id);
        entityManager.remove(product);
    }

    public Product saveOrUpdate(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        } else {
            entityManager.merge(product);
        }
        return product;
    }
}
