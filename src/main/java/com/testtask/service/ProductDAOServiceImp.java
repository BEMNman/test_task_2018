package com.testtask.service;

import com.testtask.dao.ProductDAO;
import com.testtask.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDAOServiceImp implements ProductDAOService {

//    @Autowired
    private ProductDAO productDAO;



    @Autowired
    public ProductDAOServiceImp(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Product findByName(String nameProduct) {
        return productDAO.findByName(nameProduct);
    }

    @Override
    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    @Override
    public void deleteProductByName(String nameProduct) {
        productDAO.deleteProductByName(nameProduct);
    }

    @Override
    public void update(Product product) {
        productDAO.update(product);
    }

    @Override
    public List<Product> findAllProducts() {
        return productDAO.findAllProducts();
    }

    @Override
    public List<Product> findAllProductsIsNeededTrue() {
        return productDAO.findAllProductsIsNeededTrue();
    }

    @Override
    public List<Product> findAllProductsIsNeededFalse() {
        return productDAO.findAllProductsIsNeededFalse();
    }


    @Override
    public void print() {
        System.out.println("ProductDAOServiceImp");
    }
}
