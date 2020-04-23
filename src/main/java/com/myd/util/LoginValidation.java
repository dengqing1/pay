package com.myd.util;

import com.alibaba.fastjson.JSON;
import com.myd.entity.TestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class LoginValidation {


    /**
     *
     * 功能描述: 登录成功后用UUID作为token去验证
     *
     * @auther: loujiawei
     * @date: 2018/12/3 15:12
     */
    public  void login(TestModel testModel, HttpServletRequest request, HttpServletResponse response, RedisTemplate redisTemplate) {

        //登录成功存入session信息
        request.getSession().setAttribute("name", testModel.getName());
        //设置session失效时间10分钟
        request.getSession().setMaxInactiveInterval(600);
        //登录成功用UUID做为验证令牌
        String token = UUID.randomUUID().toString().replace("-", "");
        //设置过期时间为10分钟
        redisTemplate.opsForValue().set(token, JSON.toJSONString(testModel), 60 * 10, TimeUnit.SECONDS);
        //将生成的token放入cookie中
        Cookie cookie = new Cookie(testModel.getName() + "token", token);
        response.addCookie(cookie);
    }

    /**
     *
     * 功能描述: 获取浏览器的cookie与redis匹配
     *
     * @auther: loujiawei
     * @date: 2018/12/3 15:15
     */
    public  Object validationRedisKey(Object name, HttpServletRequest request,RedisTemplate redisTemplate) {

        //获取浏览器的cookies
        Cookie[] cookies = request.getCookies();
        //将cookie信息放到map里
        Map<String, String> cookieMap = new HashMap<>();
        for (Cookie cookie : cookies) {
            cookieMap.put(cookie.getName(), cookie.getValue());
        }
        String token = cookieMap.get(name + "token");
        Object ValidationToken = redisTemplate.opsForValue().get(token);
        return ValidationToken;
    }


}
