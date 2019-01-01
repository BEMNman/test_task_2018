package com.testtask.controller;

import com.testtask.entity.Product;
import com.testtask.service.ProductDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/productList", method= RequestMethod.GET)
    public String productList() {
//        productDAOService.findAllProducts();
        productDAOService.print();
//        productDAOService.deleteProductByName("Монитор");
        return "/productList";
    }

    @RequestMapping(value = "/test1")
    public String test1() {
        return "/test1";
    }

    @RequestMapping(value = "/addProduct", method= RequestMethod.GET)
    public String addProduct() {
//        productDAOService.findAllProducts();
//        productDAOService.print();
        productDAOService.addProduct(new Product("TESTNext", (byte) 0, 10));
        return "/addProduct";
    }

    @RequestMapping(value = "/deleteProduct")
    public String deleteProduc(String nameProduct) {
        productDAOService.deleteProductByName("Монитор");
        return "/deleteProduct";
    }
}
