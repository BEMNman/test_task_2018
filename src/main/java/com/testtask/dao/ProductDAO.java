package com.testtask.dao;

import com.testtask.entity.Product;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface ProductDAO {
    ModelAndView findByName(String nameProduct);
    void addProduct(Product product);
    void deleteProduct(Product product);
    void update(Product product);
    List<Product> findAllProducts();
    List<Product> findAllProductsIsNeededTrue();
    List<Product> findAllProductsIsNeededFalse();

    ModelAndView listProducts();
}
