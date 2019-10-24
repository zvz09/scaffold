package com.zvz.scaffold.servicehi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启方法级别的保护
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //配置哪些请求需要验证
        http.authorizeRequests()
                .antMatchers("/user/registry").permitAll()
                .anyRequest().authenticated();
    }

}
