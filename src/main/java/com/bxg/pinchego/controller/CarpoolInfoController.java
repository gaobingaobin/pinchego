package com.bxg.pinchego.controller;

import com.bxg.pinchego.Util.UserUtils;
import com.bxg.pinchego.model.CarpoolInfo;
import com.bxg.pinchego.model.User;
import com.bxg.pinchego.repository.CarpoolInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


/**
 * @author gaobin
 * @createDate ${Date}
 */
@Controller
@RequestMapping("/carpoolinfo")
class CarpoolInfoController {
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
     * @createDate 2017/2/6
     * @description 获取拼车信息
    */
    @RequestMapping("/getCarpoolInfo")
    @ResponseBody
    public List<CarpoolInfo> getCarpoolInfo(@Valid CarpoolInfo carpoolInfo){
        List<CarpoolInfo> carpoolInfos = carpoolInfoRepository.findCarpoolInfoByPage(carpoolInfo);
        return carpoolInfos;
    }
}
