package ru.geekbrains.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ProductDao {

    private final EntityManagerFactory entityManagerFactory;

    public ProductDao(EntityManagerFactory emFactory) {
        this.entityManagerFactory = emFactory;
    }

    public Product findBuId(Long id) {
        return executeForEntityManager(
                entityManager -> entityManager.find(Product.class, id)
        );
    }

    public List<Product> findAll() {
        return executeForEntityManager(
                entityManager -> entityManager.createNamedQuery("allProducts", Product.class).getResultList()
        );
    }

    public void deleteById(Long id) {
        executeInTransaction(entityManager -> {
            Product product = entityManager.getReference(Product.class, id);
            entityManager.remove(product);
        });
    }

    public void saveOrUpdate(Product product) {
        executeInTransaction(entityManager -> {
            if (product.getId() == null) {
                entityManager.persist(product);
            } else {
                entityManager.merge(product);
            }
        });
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return function.apply(entityManager);
        } finally {
            entityManager.close();
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            consumer.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
