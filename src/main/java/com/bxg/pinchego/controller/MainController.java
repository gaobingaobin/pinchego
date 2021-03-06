package com.bxg.pinchego.controller;

import com.bxg.pinchego.Util.UserUtils;
import com.bxg.pinchego.Util.sha1Util;
import com.bxg.pinchego.model.CarpoolInfo;
import com.bxg.pinchego.model.User;
import com.bxg.pinchego.repository.CarpoolInfoRepository;
import com.bxg.pinchego.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
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

    @RequestMapping("/car")
    public String index(Model model) {
        List<CarpoolInfo> carpoolInfo = carpoolInfoRepository.findAll();
        model.addAttribute("arpoolInfoList", carpoolInfo);
        return "car";
    }

    @RequestMapping("/{pagename}")
    public String ticket(@PathVariable String pagename) {
        return "html/" + pagename;
    }

    /**
     * @author gaobin
     * @createDate 2017/1/10
     * @description 用户注册
     */
    @RequestMapping("/reg")
    @ResponseBody
    public String register(@Valid User user) {
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
    public String loginIn(@Valid User user, HttpSession session, HttpServletRequest request) {
        User searchUser = userRepository.findByUsernameAndPassword(user.getUsername(), sha1Util.getSha1(user.getPassword()));
        Subject currentUser = SecurityUtils.getSubject();
        if (searchUser == null) {
            request.setAttribute("loginMassage", "用户名或密码错误！");
            return "html/login";
        } else {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(searchUser.getUsername(),searchUser.getPassword());
            currentUser.login(usernamePasswordToken);
            searchUser.setLoginNumber(searchUser.getLoginNumber() + 1);
            searchUser.setLastLoginDate(new Date());
            userRepository.save(searchUser);
            session.setAttribute("currentUser", searchUser);
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);//获取跳转前的url
            // 获取保存的URL
            if (savedRequest == null || savedRequest.getRequestUrl() == null) {
                return "redirect:/car";
            }
            return "redirect:" + savedRequest.getRequestUrl();
        }


    }

    /**
     * @author gaobin
     * @createDate 2017/1/10
     * @description app登录
     */
    @RequestMapping("/appLogin")
    @ResponseBody
    public String appLogin(@Valid User user, HttpSession session) {
        User searchUser = userRepository.findByUsernameAndPassword(user.getUsername(), sha1Util.getSha1(user.getPassword()));
        Subject currentUser = SecurityUtils.getSubject();
        if (searchUser == null) {
           return "error";
        } else {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(searchUser.getUsername(),searchUser.getPassword());
            currentUser.login(usernamePasswordToken);
            searchUser.setLoginNumber(searchUser.getLoginNumber() + 1);
            searchUser.setLastLoginDate(new Date());
            userRepository.save(searchUser);
            session.setAttribute("currentUser", searchUser);
            return "success";
        }
    }

    /**
     * @author gaobin
     * @createDate 2017/1/10
     * @description 跳转登录页面
     */
    @RequestMapping("/login")
    public String login() {
        return "html/login";
    }

    /**
     * @author gaobin
     * @createDate 2017/1/12
     * @description 跳转车票预定页面
     */
    @RequestMapping("/chepiao")
    public String chepiao(Model model) {
        return "html/chepiao";
    }

    /**
     * @author gaobin
     * @createDate 2017/1/13
     * @description 跳转拼车信息发布页面
     */
    @RequestMapping("/publish")
    public String publishInfo(HttpSession session) {
        User user = UserUtils.getCurrentUser(session);
        if (user == null) {
            return "redirect:/login";
        } else {
            return "html/publish";
        }
    }


}
