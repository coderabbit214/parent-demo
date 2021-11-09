package com.jsh.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;

@Configuration
@Slf4j
public class ShiroConfig {

    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1);
//        true指定Hash散列值使用Hex加密存. false表明hash散列值用用Base64-encoded存储
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }


    @Bean("myRealm")
    @DependsOn("lifecycleBeanPostProcessor")//让这个类先于meRealm加载
    public MyRealm myRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher) {
        MyRealm myRealm = new MyRealm();
        //不启用授权缓存
        myRealm.setAuthorizationCachingEnabled(false);
        //加密机制
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myRealm;
    }

    /**
     * @Description: 定义安全管理器securityManager, 注入自定义的realm
     * 这里返回的是SessionsSecurityManager是因为在我们引入的stater包中存在的就是这个类
     * @Param: [myRealm]
     * @return: org.apache.shiro.mgt.SessionsSecurityManager
     * @Author: ywj
     * @Date: 2019/3/28 0028
     */
    @Bean("securityManager")
    public SessionsSecurityManager securityManager(@Qualifier("myRealm") MyRealm myRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm);
        return manager;
    }

    /**
     * @Description: Shiro生命周期，保证实现了Shiro内部lifecycle函数的bean执行
     * @Param: []
     * @return: org.apache.shiro.spring.LifecycleBeanPostProcessor
     * @Author: ywj
     * @Date: 2019/3/28 0028
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    /**
     * Spring的一个bean , 由Advisor决定对哪些类的方法进行AOP代理 .
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);//可以使用不是jdk的代理，
        return creator;
    }


    /**
     * @Description: 将spring和shiro关联起来
     * 开启shiro aop注解支持.  一定要写入上面defaultAdvisorAutoProxyCreator（）自动代理。不然AOP注解不会生效
     * 使用代理方式;所以需要开启代码支持;
     * @Param: [securityManager]
     * @return: org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     * @Author: ywj
     * @Date: 2019/3/28 0028
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SessionsSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


    /**
     * 定义shiroFilter过滤器并注入securityManager
     * 这里的配置其实和xml的配置大致是一样的
     *
     * @param securityManager
     * @return
     */
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SessionsSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);

        bean.setLoginUrl("/login.html");
        bean.setSuccessUrl("/user/list");
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

}
