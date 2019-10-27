package com.zvz.scaffold.serviceUser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 此类中通过 @EnableGlobalMethodSecurity(prePostEnabled = true)注解开启方法级别的安全验证
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GlobalMethodSecurityConfig {

}
