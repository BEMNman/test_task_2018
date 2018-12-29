package com.testtask.controller;

import com.testtask.service.ProductDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private ProductDAOService productDAOService;

    @RequestMapping(value = "/test", method= RequestMethod.GET)
    public String test() {
        return "/test";
    }

    @RequestMapping(value = "/test1")
    public String test1() {
        return "/test1";
    }
}
