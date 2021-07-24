package ru.geekbrains.lesson04springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ru.geekbrains.lesson04springboot.persist.Product;
import ru.geekbrains.lesson04springboot.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model, ProductListParams productListParams) {
        model.addAttribute("products", productService.findWithFilter(productListParams));
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
        model.addAttribute("product", productService.findById(Long.parseLong(id))
                .orElseThrow(()-> new NotFoundException("Product not found"))
        );
        return "product_form";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam String id) {
        productService.deleteById(Long.parseLong(id));
        return "redirect:/product";
    }

    @PostMapping
    public String updateProduct(Product product) {
        productService.save(product);
        return "redirect:/product";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
