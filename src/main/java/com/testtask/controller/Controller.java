package com.testtask.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/main")
public class Controller {
    @RequestMapping(value = "/")
    public String test() {
        return "TESTtestTEST";
    }

}
