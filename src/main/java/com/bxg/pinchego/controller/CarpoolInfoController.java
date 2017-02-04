package com.bxg.pinchego.controller;

import com.bxg.pinchego.Util.UserUtils;
import com.bxg.pinchego.model.CarpoolInfo;
import com.bxg.pinchego.model.User;
import com.bxg.pinchego.repository.CarpoolInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gaobin
 * @createDate ${Date}
 */
@Controller
@RequestMapping("/carpoolinfo")
public class CarpoolInfoController {
    @Autowired
    private CarpoolInfoRepository carpoolInfoRepository;
    /**
     * @author gaobin
     * @createDate 2017/2/4
     * @description 添加拼车信息
    */
    @RequestMapping("add")
    @ResponseBody
    public String addCarpoolInfo(@Valid CarpoolInfo carpoolInfo, HttpSession session){
        User user = UserUtils.getCurrentUser(session);
        if(user!=null){
            carpoolInfo.setUser(user);
            carpoolInfoRepository.save(carpoolInfo);
            return "success";
        }else {
            return "fialed";
        }


    }
    /**
     * @author gaobin
     * @createDate 2017/2/4
     * @description 获取拼车信息
    */
    @RequestMapping("/getCarpoolInfo")
    @ResponseBody
    public List<CarpoolInfo> getCarpoolInfo(@Valid CarpoolInfo carpoolInfo){
        List<CarpoolInfo> carpoolInfos = carpoolInfoRepository.getCarpoolInfo(carpoolInfo);
        return carpoolInfos;
    }

}
