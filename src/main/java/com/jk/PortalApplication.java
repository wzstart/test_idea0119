package com.jk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

//消费者 需要到注册中心中去寻找生产者
@EnableDiscoveryClient//可以往服务中心进行注册
@SpringBootApplication
//可以调用服务中心的项目
@EnableFeignClients
/**
 * 1.记住密码分隔符实现
 * 2.对象->json实现
 * 3.前端对字符串的截取分割遍历实现(逻辑)
 * 4.未登录时首页显示你好xxx请登录
 * 5.安装上git
 * 6.去github申请一个账号
 */
public class PortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }

}

