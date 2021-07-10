import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Service service = context.getBean("service", Service.class);

        service.getProductsByBuyerId(3L).forEach(product -> System.out.println(product.getName()));
        service.getBuyersByProductId(2L).forEach(buyer -> System.out.println(buyer.getName()));
    }
}
