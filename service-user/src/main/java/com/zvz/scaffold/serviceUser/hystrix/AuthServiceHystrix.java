package com.zvz.scaffold.serviceUser.hystrix;

import com.zvz.scaffold.serviceUser.javaBean.JWT;
import com.zvz.scaffold.serviceUser.service.AuthServiceClient;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceHystrix implements AuthServiceClient {
    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        return null;
    }
}
