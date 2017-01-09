package com.bxg.pinchego.controller;

import com.bxg.pinchego.model.User;
import com.bxg.pinchego.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

/**
 * @author gaobin
 * @createDate ${Date}
 */
@Controller
public class MainController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index(){
        return "car";
    }
    @RequestMapping("/{pagename}")
    public String ticket(@PathVariable String pagename){
        return "html/"+pagename;
    }
    @RequestMapping("/reg")
    @ResponseBody
    public String register(@Valid User user){
        user.setLoginNumber(0);
        user.setRegisterDate(new Date());
        userRepository.save(user);
       return "success";
    }
}
