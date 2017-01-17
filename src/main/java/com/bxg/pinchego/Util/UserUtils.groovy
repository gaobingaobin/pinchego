package com.bxg.pinchego.Util

import com.bxg.pinchego.model.User
import javax.servlet.http.HttpSession

/**
 * @author lidu
 * @createDate 2016/11/8
 * @description
 */
class UserUtils {

    /**
     * @author lidu
     * @createDate 2016/11/8 11:00
     * @description 获取当前登录员工的信息
     */
    public static User getCurrentUser(HttpSession session) {
        Object user = session.getAttribute("currentUser");
        if(user==null){
            return null;
        }
        return (User)user;
    }

}
