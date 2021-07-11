package ru.geekbrains.lesson04springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.lesson04springboot.persist.Product;
import ru.geekbrains.lesson04springboot.persist.ProductRepository;
import ru.geekbrains.lesson04springboot.persist.ProductSpecification;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listProducts(Model model
                               , @RequestParam("min_price") Optional <BigDecimal> minPrice
                               , @RequestParam("max_price") Optional <BigDecimal> maxPrice
                               , @RequestParam("page") Optional<Integer> page
                               , @RequestParam("page_size") Optional<Integer> size
                               , @RequestParam(value = "sort_by", defaultValue = "id") String sortBy
                               , @RequestParam("sort_dir") Optional<Sort.Direction> sortDir
                               ) {
        PageRequest pageRequest = PageRequest.of(page.orElse(1) - 1, size.orElse(3), Sort.by(sortDir.orElse(Sort.Direction.ASC), sortBy));
        Specification<Product> spec  = Specification.where(null);
        if (minPrice.isPresent()) {
            spec = spec.and(ProductSpecification.minPrice(minPrice.get()));
        }
        if (maxPrice.isPresent()) {
            spec = spec.and(ProductSpecification.maxPrice(maxPrice.get()));
        }
        model.addAttribute("products", productRepository.findAll(spec, pageRequest));
        return "products";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        Product product = new Product();

        model.addAttribute("product", product);
        return "product_form";
    }

    @GetMapping("/edit")
    public String editProduct(@RequestParam String id, Model model) {
        model.addAttribute("product", productRepository.findById(Long.parseLong(id)));
        return "product_form";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam String id) {
        productRepository.deleteById(Long.parseLong(id));
        return "redirect:/product";
    }

    @PostMapping
    public String updateProduct(Product product) {
        productRepository.save(product);
        return "redirect:/product";
    }
}
