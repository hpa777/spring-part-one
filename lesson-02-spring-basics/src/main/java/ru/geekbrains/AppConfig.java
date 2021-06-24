package ru.geekbrains;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("ru.geekbrains")
public class AppConfig {

    @Bean
    public ProductRepository productRepository() {
        ProductRepository productRepository = new ProductRepository();
        productRepository.addProduct(new Product("Товар1", 123.6F));
        productRepository.addProduct(new Product("Товар2", 123.6F));
        productRepository.addProduct(new Product("Товар3", 123.6F));
        productRepository.addProduct(new Product("Товар4", 123.6F));
        productRepository.addProduct(new Product("Товар5", 123.6F));
        return productRepository;
    }

    @Bean
    @Scope("prototype")
    public ProductRepository cart() {
        return new ProductRepository();
    }

}
