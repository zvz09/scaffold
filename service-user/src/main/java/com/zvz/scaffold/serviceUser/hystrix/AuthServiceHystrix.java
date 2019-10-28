package com.zvz.scaffold.serviceUser.hystrix;

import com.zvz.scaffold.serviceUser.javaBean.JWT;
import com.zvz.scaffold.serviceUser.service.AuthServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceHystrix implements AuthServiceClient {

    private static Logger log = LoggerFactory.getLogger(AuthServiceHystrix.class);

    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        log.error("获取token失败");
        return null;
    }
}
