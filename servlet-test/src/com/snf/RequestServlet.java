package com.snf;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class RequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
        //获取请求方式
        String method = req.getMethod(); //GET
        System.out.println(method);

        //获取请求资源路径  /servlet_test/request
        StringBuffer requestURL = req.getRequestURL(); // /servlet_test/request
        System.out.println(requestURL);

        //获取请求资源路径  http://localhost:8080/servlet_test/request //http://localhost:8080/servlet_test/request
        String requestURI = req.getRequestURI(); //http
        System.out.println(requestURL);

        //获取协议
        String scheme = req.getScheme();
        System.out.println(scheme);

        //获取请求头数据
        //根据key获取value的值
        String header = req.getHeader("User-Agent");
        System.out.println(header); //Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36

        //获取请求头中所有key的枚举对象
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String key = headerNames.nextElement();
            String value = req.getHeader(key);
            System.out.println(key + ":" +value);
        }


        //获取用户请求数据
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        System.out.println(userName + ":" + password);

        //获取所有参数key
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String key = parameterNames.nextElement();
            String value = req.getParameter(key);
            System.out.println(key + ":" + value);
        }

        //获取相同key的多个value值 eg: checkbox
        String[] favs = req.getParameterValues("fav");
        for (int i = 0; i < favs.length; i++) {
            String fav = favs[i];
            System.out.println(fav);
        }

        //其他常用方法
        //获取远程客户端的地址
        String remoteAddr = req.getRemoteAddr();
        String remoteHost = req.getRemoteHost();
        int remotePort = req.getRemotePort();
        System.out.println("remoteAddr=" + remoteAddr);
        System.out.println("remoteHost=" + remoteHost);
        System.out.println("remotePort=" + remotePort);

        String remoteUser = req.getRemoteUser();
        System.out.println("remoteUser" + remoteUser);


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
