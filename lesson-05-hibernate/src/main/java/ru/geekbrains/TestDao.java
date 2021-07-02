package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.Product;
import ru.geekbrains.entity.ProductDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class TestDao {

    public static void main(String[] args) {
        EntityManagerFactory emf = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        EntityManager em = emf.createEntityManager();
        ProductDao dao = new ProductDao(em);

        //em.getTransaction().begin();
        //dao.saveOrUpdate(new Product(null, "product2", 234));
        //dao.saveOrUpdate(new Product(null, "product3", 678));
        //em.getTransaction().commit();

        System.out.println(dao.findBuId(1L));
        em.getTransaction().begin();
        dao.saveOrUpdate(new Product(1L, "product_upd", 111));
        dao.deleteById(2L);
        em.getTransaction().commit();
        em.close();
    }
}
