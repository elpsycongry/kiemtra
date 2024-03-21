package com.example.crud.controller;

import com.example.crud.model.Product;
import com.example.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id).get();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        System.out.println(product);
        return productService.saveProduct(product);
    }

    @PutMapping()
    public Product updateProduct(@RequestBody Product productDetails) {
        Product product = productService.getProductById(productDetails.getId()).get();
        if (product != null) {
            productService.saveProduct(product);
            return product;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public List<Product> searchByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return productService.searchByPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/sort-by-amount")
    public List<Product> sortByAmount() {
        return productService.sortByAmount();
    }

    @GetMapping("/top3")
    public List<Product> getTop3ProductsByPrice() {
        return productService.getTop3ProductsByPrice();
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> getProductsByCategory(@PathVariable String categoryName) {
        return productService.getProductsByCategory(categoryName);
    }
}