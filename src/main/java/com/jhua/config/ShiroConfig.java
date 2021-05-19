package com.jhua.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        /*
         * @Author liu-miss
         * @Description //TODO anon:无需认证就可以访问
         *                 authc：必须认证了才能访问
         *                  user：必须拥有记住我功能才能用
         *                   perms：拥有对某个资源的权限才能访问
         *                      role：拥有某个角色权限才能访问
         * @Date  2021/5/19
         * @Param [defaultWebSecurityManager]
         * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
         **/
        //开启拦截
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();

//        filterMap.put("/user/add", "authc");
//        filterMap.put("/user/update", "authc");

        //授权
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");

//        filterMap.put("/user/*", "authc");

        bean.setFilterChainDefinitionMap(filterMap);

        // 设置登录的请求
        bean.setLoginUrl("/toLogin");
        // 设置未授权页面
        bean.setUnauthorizedUrl("/noauth");

        return bean;
    }

    //第二步
    //DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        //关联UserRealm
        defaultWebSecurityManager.setRealm(userRealm);


        return defaultWebSecurityManager;
    }

    //第一步
    //创建RealM
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
