package com.testtask.controller;

import com.testtask.entity.Product;
import com.testtask.service.ProductDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private ProductDAOService productDAOService;

    public Controller(ProductDAOService productDAOService) {
        this.productDAOService = productDAOService;
    }

    @RequestMapping(value = {"/", "/listProducts"}, method = RequestMethod.GET)
    public ModelAndView listProducts() {
        return productDAOService.listProducts();
    }

    @RequestMapping(value = "/searchProduct", method = RequestMethod.GET)
    public ModelAndView searchProduct(@RequestParam("search") String nameProduct) {
        return productDAOService.findByName(nameProduct);
    }
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public ModelAndView addProduct() {
        Product productAdd = new Product("TESTNext", (byte) 0, 10);
        productDAOService.addProduct(productAdd);
        return listProducts();
    }

    @RequestMapping(value = "/deleteProduct")
    public ModelAndView deleteProduct(Product productDelete) {
        productDelete = new Product("Монитор", (byte) 1, 33); ///for test
        productDAOService.deleteProduct(productDelete);
        return listProducts();
    }


    @RequestMapping(value = "/updateProduct")
    public String updateProduct(Product productUpdate) {
        productUpdate = new Product("Монитор", (byte) 1, 33); ///for test
        productDAOService.deleteProduct(productUpdate);
        return "/updateProduct";
    }

    @RequestMapping(value = "/test1")
    public String test1() {
        return "/test1";
    }
}
