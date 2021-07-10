import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.geekbrains.entity.BuyerDao;
import ru.geekbrains.entity.OrderDao;
import ru.geekbrains.entity.ProductDao;

import javax.persistence.EntityManagerFactory;

@Configuration
@ComponentScan("ru.geekbrains")
public class AppConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    @Bean
    public OrderDao orderDao(EntityManagerFactory entityManagerFactory) {
        return new OrderDao(entityManagerFactory);
    }

    @Bean
    public BuyerDao buyerDao(EntityManagerFactory entityManagerFactory) {
        return new BuyerDao(entityManagerFactory);
    }

    @Bean
    public ProductDao productDao(EntityManagerFactory entityManagerFactory) {
        return new ProductDao(entityManagerFactory);
    }

    @Bean
    public Service service(OrderDao orderDao) {
        return new Service(orderDao);
    }
}
