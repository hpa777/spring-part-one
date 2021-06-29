package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductRepository products = context.getBean("productRepository", ProductRepository.class);

        ProductRepository cart1 = context.getBean("cart", ProductRepository.class);
        cart1.addProduct(products.getProductById(1L));
        cart1.addProduct(products.getProductById(2L));
        cart1.deleteProductById(1L);
        System.out.println(cart1.getAllProducts());

        ProductRepository cart2 = context.getBean("cart", ProductRepository.class);
        cart2.addProduct(products.getProductById(1L));
        cart2.addProduct(products.getProductById(2L));
        cart2.deleteProductById(1L);
        cart2.deleteProductById(2L);
        System.out.println(cart2.getAllProducts());


    }
}
