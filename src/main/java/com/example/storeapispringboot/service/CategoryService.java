package com.example.storeapispringboot.service;

import com.example.storeapispringboot.Repository.CategoryRepository;
import com.example.storeapispringboot.model.Category;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category with id: " + id + " does not exist"));
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategoryById(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category with id: " + id + " does not exist");
        }
    }

    @Transactional
    public void updateCategory(Category category) {
        Category categoryToUpdate = categoryRepository.findById(category.getId()).orElseThrow(() -> new RuntimeException("Category with id: " + category.getId() + " does not exist"));
        if (category.getName() != null) {
            categoryToUpdate.setName(category.getName());
        }
        if (category.getProducts() != null) {
            categoryToUpdate.setProducts(category.getProducts());
        }
    }
}
