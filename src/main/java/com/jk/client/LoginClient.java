package com.jk.client;

import com.jk.bean.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("provider-login")
public interface LoginClient {

    @RequestMapping("login")
        //基本数据类型可以使用 RequestParam
        //如果是自定义的类型 如user  @requestBody
        // User invokeLogin(@RequestParam("loginacct") String loginacct, @RequestParam("userpswd") String userpswd);
    User invokeLogin(User user);
}
