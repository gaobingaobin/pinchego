package com.bxg.pinchego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
    @RequestMapping("/{pagename}")
    public String ticket(@PathVariable String pagename){
        return "html/"+pagename;
    }
    @RequestMapping("/loginIn")
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

       return "";

    }
}
