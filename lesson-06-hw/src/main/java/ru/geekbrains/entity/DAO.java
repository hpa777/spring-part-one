package ru.geekbrains.entity;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.function.Consumer;
import java.util.function.Function;


abstract class DAO <T extends IEntity>  {

    protected EntityManagerFactory entityManagerFactory;

    private final Class<T> tClass;

    public DAO(Class<T> entityClass, EntityManagerFactory emf) {
        this.entityManagerFactory = emf;
        this.tClass = entityClass;
    }

    public T findById(Long id) {
        return executeForEntityManager(
                entityManager -> entityManager.find(tClass, id)
        );
    }

    public void deleteById(Long id) {
        executeInTransaction(entityManager -> {
            T entity = entityManager.getReference(tClass, id);
            entityManager.remove(entity);
        });
    }

    public void insert(T entity) {
        executeInTransaction(entityManager -> entityManager.persist(entity));
    }

    public void update(T entity) {
        executeInTransaction(entityManager -> entityManager.merge(entity));
    }

    protected <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return function.apply(entityManager);
        } finally {
            entityManager.close();
        }
    }

    protected void executeInTransaction(Consumer<EntityManager> consumer) {
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
