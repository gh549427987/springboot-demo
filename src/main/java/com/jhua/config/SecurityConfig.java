package com.jhua.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpSession;

//AOP: 跟拦截器差不多
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 链式编程
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/R/**").hasRole("R")
                .antMatchers("/SR/**").hasRole("SR")
                .antMatchers("/SSR/**").hasRole("SSR")
                .antMatchers("/level1/**").hasRole("R")
                .antMatchers("/level2/**").hasRole("SR")
                .antMatchers("/level3/**").hasRole("SSR");

        http.formLogin();

//        http.csrf().disable();//关闭csrf功能
        //注销
        http.logout().logoutSuccessUrl("/");
    }

    //认证
    //密码编码：PasswordEncoder
    //在Spring Security 5.0+ 新增了许多加密方法
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 下面这些数据正常应该从数据库中读取
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("jhua").password(new BCryptPasswordEncoder().encode("1")).roles("SSR", "SR", "R")
                .and()
                .withUser("manR").password(new BCryptPasswordEncoder().encode("1")).roles("R")
                .and()
                .withUser("manSR").password(new BCryptPasswordEncoder().encode("1")).roles("SR", "R");
    }
}
