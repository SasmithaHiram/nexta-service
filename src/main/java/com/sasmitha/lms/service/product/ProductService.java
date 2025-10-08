package com.sasmitha.lms.service.product;

import com.sasmitha.lms.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product getProductById(Long id);
    void updateProduct(Product product, Long id);
    void deleteProduct(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand, String name);
}
