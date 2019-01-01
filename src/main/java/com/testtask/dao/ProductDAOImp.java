package com.testtask.dao;

import com.testtask.entity.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImp implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Product findByName(final String name) {
        Session session = this.sessionFactory.getCurrentSession();
        System.out.println("start find");
//        Query query = session.createQuery("from Product where nameProduct like 'Монитор'");
//        System.out.println("after query");
////        query.setParameter("nameProduct", nameProduct);
//        List<Product> productWithName = null;
//        productWithName.addAll(query.getResultList());
//        System.out.println("end");
//        //
//        for(Product p : productWithName){
//            System.out.println("in loop");
//            System.out.println(p.getNameProduct());
//        }
//        Criteria criteria = session.
        Query query = session.createQuery("from Product p where p.nameProduct like 'Монитор'");
        System.out.println("after query");
//        query.setParameter("name", nameProduct);
        List<Product> productWithName = new ArrayList<Product>();
        productWithName.addAll(((org.hibernate.query.Query) query).list());
//        productWithName.addAll(query.getResultList());
        System.out.println("end");
        //
        for(Product p : productWithName){
            System.out.println("in loop");
            System.out.println(p);
        }
        //
        return productWithName.size() == 1 ? productWithName.get(0) : null;
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(product);

    }

    @Override
    @Transactional
    public void deleteProductByName(String nameProduct) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(findByName(nameProduct));
//        session.delete(1);
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
        List<Product> products = null;
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select p.* from product p").addEntity(Product.class);
        products.addAll(query.getResultList());
        return products;
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
