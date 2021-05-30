package com.snf;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取浏览器cookie
        Cookie[] cookies = req.getCookies();
        if (cookies.length > 0){
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                String key = cookie.getName();
                String value = cookie.getValue();
                System.out.println(key + ":" +value);
            }
        }

        //添加cookie
        Cookie cookie1 = new Cookie("0001","hello");
        //cookie持久化
        cookie1.setMaxAge(60*5);

        Cookie cookie2 = new Cookie("0002", "world");
        //给cookie设置路径
        cookie2.setPath("/cookie/abc");

        resp.addCookie(cookie1);
        resp.addCookie(cookie2);
        resp.getWriter().write("set cookie");


    }
}
