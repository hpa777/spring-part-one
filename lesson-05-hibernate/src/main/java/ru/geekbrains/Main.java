package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        EntityManager em = emf.createEntityManager();
        /*
        em.getTransaction().begin();
        List.of(
                new User(null, "user2", 25),
                new User(null, "user3", 67),
                new User(null, "user4", 23),
                new User(null, "user5", 45),
                new User(null, "user6", 30),
                new User(null, "user7", 29)
        ).forEach(em::persist);

         */

        //User user = em.find(User.class, 1L);
        List<User> users =  em.createQuery("select u from User u where u.age > :age", User.class)
                .setParameter("age", 25)
                .getResultList();
        System.out.println(users);
        Long c = em.createNamedQuery("countUsers", Long.class).getSingleResult();
        System.out.println(c);
        users = em.createNativeQuery("select * from users", User.class).getResultList();
        System.out.println(users);

        User user = em.getReference(User.class, 7L);
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }
}
