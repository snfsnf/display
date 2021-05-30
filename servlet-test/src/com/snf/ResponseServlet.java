package com.snf;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("this is get");
        //设置响应头按照key-value形式,覆盖
        resp.setHeader("hehe","haha");
        resp.setHeader("hehe","heihei");
        //设置响应头按照key-value形式，不覆盖
        resp.addHeader("city","beijing");
        resp.addHeader("city","shanghai");

        //设置响应状态码
//        resp.sendError(404,"not found");

        //服务端返回的对象数据按照一定格式进行渲染
//        resp.setHeader("Content-Type","text/html");
        resp.setHeader("Content-Type","text/plain");
//        resp.setContentType("text/html");
        resp.getWriter().write("<b>java is easy</b>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
