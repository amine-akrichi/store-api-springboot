package com.example.storeapispringboot.controller;
import com.example.storeapispringboot.model.Product;
import com.example.storeapispringboot.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")
@Slf4j
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping(path = "{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        log.info("ProductController.saveProduct: " + product);
        return productService.saveProduct(product);
    }

    @PutMapping(path = "{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        productService.updateProduct(product);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }


}
