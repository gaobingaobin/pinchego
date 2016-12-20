package com.bxg.pinchego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gaobin
 * @createDate ${Date}
 */
@Controller
public class MainController {
    @RequestMapping("/")
    public String index(){
        return "car";
    }
}
