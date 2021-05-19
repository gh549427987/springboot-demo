package com.jhua.config;

import com.jhua.Service.UserService;
import com.jhua.pojo.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了->授权doGetAuthorizationInfo");

        //SimpleAuthorizationInfo
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("user:add");

        //拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal(); //拿到User

        //设置当前用户的权限
        info.addStringPermission(currentUser.getPerms());


        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了->认证doGetAuthorizationInfo");

        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        //用户名和密码 —— 从数据中获取
        User user = userService.queryUserByName(userToken.getUsername());

        if (user == null) {
            return null;
        }

        //密码认证 由shiro做
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");//三个参数，一个是认证，一个是密码，一个是认证名
    }
}
