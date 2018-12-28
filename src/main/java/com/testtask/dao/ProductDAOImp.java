package com.testtask.dao;

import com.testtask.entity.Product;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProductDAOImp implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Product findByName(final String nameProduct) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select p from product p where p.product like " + nameProduct).addEntity(Product.class);
        List<Product> productWithName = null;
        productWithName.addAll(query.list());
        return productWithName.size() == 1 ? productWithName.get(0) : null;
    }

    @Override
    public void addProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(product);

    }

    @Override
    public void deleteProductByName(String nameProduct) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(findByName(nameProduct).getId());
    }

    @Override
    public void update(Product product) {
        Session session = sessionFactory.getCurrentSession();
        Product productExist = session.get(Product.class, product.getId());
        productExist.setNameProduct(product.getNameProduct());
        productExist.setIsNeeded(product.getIsNeeded());
        productExist.setAmount(product.getAmount());
        session.save(productExist);
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = null;
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("from product").addEntity(Product.class);
        products.addAll(query.list());
        return products;
    }

    @Override
    public List<Product> findAllProductsIsNeededTrue() {
        List<Product> productsIsNeeded = null;
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select p from product p where p.amount like 1").addEntity(Product.class);
        productsIsNeeded.addAll(query.list());
        return productsIsNeeded;
    }

    @Override
    public List<Product> findAllProductsIsNeededFalse() {
        List<Product> productsNotNeeded = null;
        Session session = this.sessionFactory.getCurrentSession();
//        Query query = session.createQuery("select p from product p where p.amount like '0'");
        Query query = session.createSQLQuery("select p from product p where p.amount like 0").addEntity(Product.class);
        productsNotNeeded.addAll(query.list());
        return productsNotNeeded;
    }

}
