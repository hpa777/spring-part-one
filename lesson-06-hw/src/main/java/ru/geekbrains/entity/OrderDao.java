package ru.geekbrains.entity;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class OrderDao extends DAO<Order> {

    public OrderDao(EntityManagerFactory emf) {
        super(Order.class, emf);
    }

    public List<Product> listOfPurchasedProductsByBuyerId(Long buyerId) {
        return executeForEntityManager(
                entityManager -> entityManager.createNamedQuery("getProductsByBuyerId", Product.class).setParameter("id", buyerId).getResultList()
        );
    }

    public List<Buyer> listOfBuyersByProductId(Long productId) {
        return executeForEntityManager(
                entityManager -> entityManager.createNamedQuery("getBuyersByProductId", Buyer.class).setParameter("id", productId).getResultList()
        );
    }
}
