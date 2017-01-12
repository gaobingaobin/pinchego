package com.bxg.pinchego.controller;

import com.bxg.pinchego.Util.sha1Util;
import com.bxg.pinchego.model.CarpoolInfo;
import com.bxg.pinchego.model.User;
import com.bxg.pinchego.repository.CarpoolInfoRepository;
import com.bxg.pinchego.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author gaobin
 * @createDate ${Date}
 */
@Controller
public class MainController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CarpoolInfoRepository carpoolInfoRepository;

    @RequestMapping("/")
    public String index(Model model){
        List<CarpoolInfo> carpoolInfo = carpoolInfoRepository.findAll();
        model.addAttribute("arpoolInfoList",carpoolInfo);
        return "car";
    }
    @RequestMapping("/{pagename}")
    public String ticket(@PathVariable String pagename){
        return "html/"+pagename;
    }
    /**
     * @author gaobin
     * @createDate 2017/1/10
     * @description 用户注册
    */
    @RequestMapping("/reg")
    @ResponseBody
    public String register(@Valid User user){
        user.setLoginNumber(0);
        user.setRegisterDate(new Date());
        user.setPassword(sha1Util.getSha1(user.getPassword()));
        userRepository.save(user);
       return "success";
    }
    /**
     * @author gaobin
     * @createDate 2017/1/10
     * @description 用户登录
    */
    @RequestMapping("/loginIn")
    public String loginIn(@Valid User user, HttpSession session,HttpServletRequest request){
        User searchUser = userRepository.findByUsernameAndPassword(user.getUsername(),sha1Util.getSha1(user.getPassword()));
        if(searchUser==null){
            request.setAttribute("loginMassage", "用户名或密码错误！");
            return "html/login";
        }else{
            searchUser.setLoginNumber(searchUser.getLoginNumber()+1);
            searchUser.setLastLoginDate(new Date());
            userRepository.save(searchUser);
            session.setAttribute("currentUser",searchUser);
            return "redirect:/";
        }


    }

    /**
     * @author gaobin
     * @createDate 2017/1/10
     * @description 跳转登录页面
    */
    @RequestMapping("/login")
    public String login(){
        return "html/login";
    }
    /**
     * @author gaobin
     * @createDate 2017/1/12
     * @description 跳转车票预定页面
    */
    @RequestMapping("/chepiao")
    public String chepiao(Model model){
        List<CarpoolInfo> carpoolInfo = carpoolInfoRepository.findAll();
        model.addAttribute("arpoolInfoList",carpoolInfo);
        return "html/chepiao";
    }



}
