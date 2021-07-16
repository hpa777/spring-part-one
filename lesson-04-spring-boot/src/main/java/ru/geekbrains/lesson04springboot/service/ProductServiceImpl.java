package ru.geekbrains.lesson04springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.lesson04springboot.controller.ProductListParams;
import ru.geekbrains.lesson04springboot.persist.Product;
import ru.geekbrains.lesson04springboot.persist.ProductRepository;
import ru.geekbrains.lesson04springboot.persist.ProductSpecification;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findWithFilter(ProductListParams productListParams) {
        Specification<Product> spec  = Specification.where(null);
        if (productListParams.getMinPrice() != null) {
            spec = spec.and(ProductSpecification.minPrice(productListParams.getMinPrice()));
        }
        if (productListParams.getMaxPrice() != null) {
            spec = spec.and(ProductSpecification.maxPrice(productListParams.getMaxPrice()));
        }

        Sort sort = Sort.by(Sort.Direction.fromString(Optional.ofNullable(productListParams.getSortDir()).filter(c->!c.isBlank()).orElse("ASC"))
                , Optional.ofNullable(productListParams.getSortBy()).filter(c->!c.isBlank()).orElse("id")
        );
        PageRequest pageRequest = PageRequest.of(Optional.ofNullable(productListParams.getPage()).orElse(1) - 1
                , Optional.ofNullable(productListParams.getPageSize()).orElse(3)
                , sort
        );
        return productRepository.findAll(spec, pageRequest);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long Id) {
        productRepository.deleteById(Id);
    }
}
