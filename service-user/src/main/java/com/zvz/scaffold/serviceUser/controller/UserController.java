package com.zvz.scaffold.serviceUser.controller;

import com.zvz.scaffold.serviceUser.dto.UserLoginDTO;
import com.zvz.scaffold.serviceUser.entity.User;
import com.zvz.scaffold.serviceUser.service.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceDetail userServiceDetail;

    @PostMapping("/register")
    public User postUser(@RequestParam("username") String username , @RequestParam("password") String password){
        //参数判断，省略
        return userServiceDetail.insertUser(username,password);
    }

    @PostMapping("/login")
    public UserLoginDTO login(@RequestParam("username") String username , @RequestParam("password") String password){
        //参数判断，省略
        return userServiceDetail.login(username,password);
    }

    @RequestMapping(value = "/foo", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getFoo() {
        return "i'm foo, " + UUID.randomUUID().toString();
    }
}
