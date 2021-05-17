package com.jhua.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
                .antMatchers("/SSR/**").hasRole("SSR");

        http.formLogin();
    }

    //认证

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("jhua").password("123456").roles("SSR", "SR", "R")
                .and()
                .withUser("manR").password("123").roles("R")
                .and()
                .withUser("manSR").password("123456").roles("SR", "R");
    }
}
