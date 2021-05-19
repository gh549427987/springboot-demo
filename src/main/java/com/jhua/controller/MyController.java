package com.jhua.controller;

import com.jhua.pojo.model.User;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;

@Controller
public class MyController {

    @ApiOperation(value = "Index页面", notes = "初始界面", httpMethod = "GET")
    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello, Shiro");
        return "index";
    }

    @ApiOperation(value = "Add页面", notes = "add界面", httpMethod = "GET")
    @RequestMapping("/user/add")
    public String add() {
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal(); //拿到User
        System.out.println(currentUser.getPerms());
        return "user/add";
    }

    @ApiOperation(value = "Update页面", notes = "update界面", httpMethod = "GET")
    @RequestMapping("/user/update")
    public String update() {
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal(); //拿到User
        System.out.println(currentUser.getPerms());
        return "user/update";
    }

    @ApiOperation(value = "登录", notes = "login", httpMethod = "POST")
    @RequestMapping("/usr/login")
    public String login(String username, String password, Model model) {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();

        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token); // 执行登录的方法，如果没有异常就说明OK了
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名错误");
            return "views/login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "views/login";
        }
    }

    @ApiOperation(value = "无权限页面", notes = "noauth界面", httpMethod = "GET")
    @RequestMapping("/noauth")
    @ResponseBody
    public String unauthorized() {
        return "未经授权无法访问此页面";
    }
}
