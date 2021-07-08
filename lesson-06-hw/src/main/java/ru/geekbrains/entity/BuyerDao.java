package ru.geekbrains.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class BuyerDao extends DAO<Buyer> {


    public BuyerDao(EntityManagerFactory emf) {
        super(Buyer.class, emf);

    }
}
