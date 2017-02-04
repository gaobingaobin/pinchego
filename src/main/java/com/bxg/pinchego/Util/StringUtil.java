package com.bxg.pinchego.Util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by lhp on 14/10/29.
 */
public class StringUtil {

    public static Set<String> formatStringsToSet(String ids) {

        Set<String> idSet = new HashSet<String>();

        ids = ids.trim();

        if(ids != null && !"".equals(ids)) {
            String[] idList = ids.split(",");
            for(String s : idList) {
                idSet.add(s.trim());
            }
        }

        return idSet;
    }

    public static boolean isBlank( String value ){
        if( null == value || "".equals( value ) ){
            return true;
        }
        return false;
    }

    public static boolean isNotBlank( String value ){
        if( null != value && !"".equals( value ) ){
            return true;
        }
        return false;
    }


    public static String getRandomCode() {

        return UUID.randomUUID().toString();
    }

    public static boolean checkFormToken(HttpServletRequest request) {
        String formToken = request.getParameter("formToken");
        if(isNotBlank(formToken) && formToken.equals(request.getSession().getAttribute("formToken"))){
            request.getSession().removeAttribute("formToken");
            return true;
        }
        return false;
    }

    public static void resetFormTokenToSesseion(HttpServletRequest request){
        String formToken = request.getParameter("formToken");
        if(isNotBlank(formToken)){
            request.getSession().setAttribute("formToken",formToken);
        }
    }
}
