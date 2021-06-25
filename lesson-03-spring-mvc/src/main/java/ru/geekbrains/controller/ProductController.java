package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

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
    public String listProducts(Model model) {
        model.addAttribute("products", productRepository.getAllProducts());
        return "products";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @GetMapping("/edit")
    public String editProduct(@RequestParam String id, Model model) {
        model.addAttribute("product", productRepository.getProductById(Long.parseLong(id)));
        return "product_form";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam String id) {
        productRepository.deleteProductById(Long.parseLong(id));
        return "redirect:/product";
    }

    @PostMapping
    public String updateProduct(Product product) {
        productRepository.addProduct(product);
        return "redirect:/product";
    }
}
