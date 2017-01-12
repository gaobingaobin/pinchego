package com.bxg.pinchego.controller;

import com.bxg.pinchego.model.CarpoolInfo;
import com.bxg.pinchego.repository.CarpoolInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.validation.Valid;

/**
 * @author gaobin
 * @createDate ${Date}
 */
@Controller
@RequestMapping("/carpoolinfo")
public class CarpoolInfoController {
    @Autowired
    CarpoolInfoRepository carpoolInfoRepository;
    @RequestMapping("add")
    @ResponseBody
    public String addCarpoolInfo(@Valid CarpoolInfo carpoolInfo){
        carpoolInfoRepository.save(carpoolInfo);
        return "success";

    }
}
