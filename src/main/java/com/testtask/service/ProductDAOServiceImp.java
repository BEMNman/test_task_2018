package com.testtask.service;

import com.testtask.dao.ProductDAO;
import com.testtask.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductDAOServiceImp implements ProductDAOService {

    @Autowired
    private ProductDAO productDAO;

    @Transactional
    @Override
    public Product findByName(String nameProduct) {
        return productDAO.findByName(nameProduct);
    }

    @Transactional
    @Override
    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    @Transactional
    @Override
    public void deleteProductByName(String nameProduct) {
        productDAO.deleteProductByName(nameProduct);
    }

    @Transactional
    @Override
    public void update(Product product) {
        productDAO.update(product);
    }

    @Transactional
    @Override
    public List<Product> findAllProducts() {
        return productDAO.findAllProducts();
    }

    @Transactional
    @Override
    public List<Product> findAllProductsIsNeededTrue() {
        return productDAO.findAllProductsIsNeededTrue();
    }

    @Transactional
    @Override
    public List<Product> findAllProductsIsNeededFalse() {
        return productDAO.findAllProductsIsNeededFalse();
    }
}
