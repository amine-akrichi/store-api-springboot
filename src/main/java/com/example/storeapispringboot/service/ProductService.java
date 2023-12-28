package com.example.storeapispringboot.service;

import com.example.storeapispringboot.Repository.ProductRepository;
import com.example.storeapispringboot.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product with id: " + id + " does not exist"));
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product with id: " + id + " does not exist");
        }
    }

    @Transactional
    public void updateProduct(Product product) {
        Product productToUpdate = productRepository.findById(product.getId()).orElseThrow(() -> new RuntimeException("Product with id: " + product.getId() + " does not exist"));
        if (product.getName() != null) {
            productToUpdate.setName(product.getName());
        }
        if (product.getPrice() != null) {
            productToUpdate.setPrice(product.getPrice());
        }
        if (product.getQuantity() != 0) {
            productToUpdate.setQuantity(product.getQuantity());
        }
//        if (product.getCategory() != null) {
//            productToUpdate.setCategory(product.getCategory());
//        }
    }
}
