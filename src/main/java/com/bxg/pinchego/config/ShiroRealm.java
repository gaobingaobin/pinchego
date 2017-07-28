package com.bxg.pinchego.config;

import com.bxg.pinchego.model.Permission;
import com.bxg.pinchego.model.User;
import com.bxg.pinchego.repository.UserRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: gaobin
 * Date: 2017/5/22
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    UserRepository userRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String loginName = (String)super.getAvailablePrincipal(principalCollection);
        User user = userRepository.findByUsername(loginName);
        if(user != null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Set<Permission> permissions = user.getPermissions();
            for(Permission permission:permissions){
                info.addStringPermission(permission.getName());
            }
            return info;
        }
        // 自动跳转到unauthorizedUrl指定的地址
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //查出是否有此用户
        User user = userRepository.findByUsername(token.getUsername());
        if(user != null){
            // 若户存放到登录认证info中，Shiro会为我们进行密码对比校验
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        }
        return null;
    }
}
