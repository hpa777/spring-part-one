package ru.geekbrains.entity;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductDao extends DAO<Product>{


    public ProductDao(EntityManagerFactory emf) {
        super(Product.class, emf);
    }

    public List<Product> listOfPurchasedProductsByBuyerId() {
        return executeForEntityManager(entityManager -> entityManager.createNamedQuery("allProducts", Product.class).getResultList());
    }
}
