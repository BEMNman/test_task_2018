package com.testtask.dao;

import com.testtask.entity.Product;

import java.util.List;

public interface ProductDAO {
    Product findByName(String nameProduct);
    void addProduct(Product product);
    void deleteProductByName(String nameProduct);
    void update(Product product);
    List<Product> findAllProducts();
    List<Product> findAllProductsIsNeededTrue();
    List<Product> findAllProductsIsNeededFalse();
}
