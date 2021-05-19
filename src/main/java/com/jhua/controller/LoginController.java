package com.jhua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String index() {
        System.out.println("index");
        return "index";
    }

    @RequestMapping("/usr/login")
    public String login() {
        System.out.println("login");
        return "index";
    }

    @RequestMapping({"/toLogin"})
    public String afterLogin() {
        System.out.println("toLogin");
        return "views/login";
    }

    @RequestMapping("/level1/{id}")
    public String r(@PathVariable("id") int id) {
        return "views/level1/"+id;
    }

    @RequestMapping("/level2/{id}")
    public String sr(@PathVariable("id") int id) {
        return "views/level2/"+id;
    }

    @RequestMapping("/level3/{id}")
    public String ssr(@PathVariable("id") int id) {
        return "views/level3/"+id;
    }

}
