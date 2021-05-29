package com.snf;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
        //获取请求方式
        String method = req.getMethod(); //GET

        //获取请求资源路径  /servlet_test/request
        StringBuffer requestURL = req.getRequestURL(); // /servlet_test/request

        //获取请求资源路径  http://localhost:8080/servlet_test/request //http://localhost:8080/servlet_test/request
        String requestURI = req.getRequestURI(); //http

        //获取协议
        String scheme = req.getScheme();

        System.out.println(method);
        System.out.println(requestURI);
        System.out.println(requestURL);
        System.out.println(scheme);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");
        String method = req.getMethod();
        StringBuffer requestURL = req.getRequestURL();
        String requestURI = req.getRequestURI();

        System.out.println(method);
        System.out.println(requestURI);
        System.out.println(requestURL);
    }
}
