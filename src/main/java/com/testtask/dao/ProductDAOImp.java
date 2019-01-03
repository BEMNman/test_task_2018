package com.testtask.dao;

import com.testtask.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImp implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public ModelAndView findByName(final String name) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product p where p.nameProduct like '%" + name.trim() + "%'");
        List<Product> productWithName = new ArrayList<Product>();
        productWithName.addAll(((org.hibernate.query.Query) query).list());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", productWithName);
        modelAndView.setViewName("listProducts");
        return modelAndView;
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        if (findByName(product.getNameProduct()) == null) {
            session.persist(product);
        } else {
            System.out.println("Retry add new product");
        }
    }

    @Override
    @Transactional
    public void deleteProduct(Product product) {
//        Session session = this.sessionFactory.getCurrentSession();
////        Product productDelete = findByName(product.getNameProduct());
//        if (productDelete != null) {
//            session.delete(productDelete);
//        } else {
//            System.out.println("Retry delete new product");
//        }
    }

    @Override
    @Transactional
    public void update(Product product) {
        Session session = sessionFactory.getCurrentSession();
        Product productExist = session.get(Product.class, product.getId());
        productExist.setNameProduct(product.getNameProduct());
        productExist.setIsNeeded(product.getIsNeeded());
        productExist.setAmount(product.getAmount());
        session.save(productExist);
    }

    @Override
    @Transactional
    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product");
        products.addAll(((org.hibernate.query.Query) query).list());
        return products;
    }

    @Override
    @Transactional
    public ModelAndView listProducts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", findAllProducts());
        modelAndView.setViewName("listProducts");
        return modelAndView;
    }

    @Override
    @Transactional
    public List<Product> findAllProductsIsNeededTrue() {
        List<Product> productsIsNeeded = null;
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select p from product p where p.amount like 1").addEntity(Product.class);
        productsIsNeeded.addAll(query.getResultList());
        return productsIsNeeded;
    }

    @Override
    @Transactional
    public List<Product> findAllProductsIsNeededFalse() {
        List<Product> productsNotNeeded = null;
        Session session = this.sessionFactory.getCurrentSession();
//        Query query = session.createQuery("select p from product p where p.amount like '0'");
        Query query = session.createSQLQuery("select p from product p where p.amount like 0").addEntity(Product.class);
        productsNotNeeded.addAll(query.getResultList());
        return productsNotNeeded;
    }

}
