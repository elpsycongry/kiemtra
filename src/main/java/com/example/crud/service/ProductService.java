package com.example.crud.service;

import com.example.crud.model.Product;
import com.example.crud.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    IProductRepository productRepository;

    @Autowired
    CategoryService categoryService;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    public Product saveProduct(Product product) {
        categoryService.saveCategory(product.getCategory());
        return productRepository.save(product);
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public List<Product> searchByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
    public List<Product> sortByAmount() {
        return productRepository.findAllByOrderByAmountDesc();
    }
    public List<Product> getTop3ProductsByPrice() {
        return productRepository.findTop3ByOrderByPriceDesc();
    }
    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }
}
