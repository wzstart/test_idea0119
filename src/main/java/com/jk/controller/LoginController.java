package com.jk.controller;

import com.alibaba.fastjson.JSONObject;
import com.jk.bean.User;
import com.jk.client.LoginClient;
import com.jk.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("user")
public class LoginController {

    @Autowired
    LoginClient loginClient;

    @RequestMapping("toLogin")
    public String toLogin(HttpServletRequest request, Model model) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constant.remPwd.equals(cookie.getName())) {
                    String msg = cookie.getValue();
                    String[] userAndPwd = msg.split(Constant.splitChar);
                    model.addAttribute("username", userAndPwd[0]);
                    model.addAttribute("password", userAndPwd[1]);
                }

                //把json-->user对象
                //URLDecoder.decode(cookies[i].getName(),"utf-8")
            }
        }
        return "login";
    }

    @ResponseBody
    @RequestMapping("login")
    public String login(User user, HttpSession session, HttpServletResponse response) throws UnsupportedEncodingException {

        //调用登录服务
        User userFromProvider = loginClient.invokeLogin(user);

        if (userFromProvider == null) {
            //错误 --> 没有任何逻辑

            return "0";//用户名或者密码错误
        }

        session.setAttribute("user", user);

        //用户名和密码正确
        // 判断有没有勾选记住密码
        if (user.getRemePwd() != null) {
            //勾选--> 记住
            //往客户端发送cookie 一个站点只能存放20 一个浏览器只能存放400 每个cookie的大小在2kb以内

            //1.以分隔符方式分割用户名和密码
            //2.把user对象-->json字符串 !!!!!!!!!一定要实现
            String userText = JSONObject.toJSONString(user);
            //进行编码
            String encode = URLEncoder.encode(userText, "utf-8");
            Cookie cookie = new Cookie(Constant.remPwd, user.getLoginacct() + Constant.splitChar + user.getUserpswd());
            Cookie pwd = new Cookie("pwd", encode);
            response.addCookie(pwd);
            //设置过期时间 保存7天
            cookie.setMaxAge(604800);
            //当前应用任何目录下都能访问cookie
            cookie.setPath("/");
            //此时的cookie还在服务器上 要发送到浏览器上 通过响应对象
            response.addCookie(cookie);

            Cookie test = new Cookie("test", "test_val");
            response.addCookie(test);
        } else {
            //没有勾选-->清除密码
            Cookie cookie = new Cookie(Constant.remPwd, "");
            //maxAge=0会删除cookie
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        return "1";
    }


    @RequestMapping("toView")
    public String toView(String viewName) {
        System.out.println("客户端加上的一行代码");

  System.out.println("服务端加上的一行代码");
         System.out.println("服务端加上的二行代码");
        return viewName;
    }

}
