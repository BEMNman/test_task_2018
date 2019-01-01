package com.testtask.controller;

import com.testtask.entity.Product;
import com.testtask.service.ProductDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private ProductDAOService productDAOService;

    public Controller(ProductDAOService productDAOService) {
        this.productDAOService = productDAOService;
    }

    @RequestMapping(value = {"/", "/start"}, method = RequestMethod.GET)
    public String start() {
        return "/start";
    }

    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public String productList() {
//        productDAOService.findAllProducts();
        productDAOService.print();
//        productDAOService.deleteProductByName("Монитор");
        return "/productList";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listProducts() {
        return productDAOService.listProducts();
    }

    @RequestMapping(value = "/test1")
    public String test1() {
        return "/test1";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String addProduct() {
        Product productAdd = new Product("TESTNext", (byte) 0, 10);
        productDAOService.addProduct(productAdd);
        return "/addProduct";
    }

    @RequestMapping(value = "/deleteProduct")
    public String deleteProduct(Product productDelete) {
        productDelete = new Product("Монитор", (byte) 1, 33); ///for test
        productDAOService.deleteProduct(productDelete);
        return "/deleteProduct";
    }
}
