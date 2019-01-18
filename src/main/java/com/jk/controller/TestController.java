package com.jk.controller;

import com.jk.bean.User;
import com.jk.client.LoginClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @Autowired
    LoginClient loginClient;

    @RequestMapping("test")
    public User test(User user) {

        return loginClient.invokeLogin(user);
    }


}
