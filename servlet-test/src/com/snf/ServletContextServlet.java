package com.snf;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1.ServletContext由服务器创建，
 * 2.一个web应用共同拥有一个ServletContext对象
 */
public class ServletContextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取ServletContext对象
        //1.
        ServletContext servletContext1 = this.getServletContext();

        //2.
        ServletContext servletContext2 = this.getServletConfig().getServletContext();

        //3.
        ServletContext servletContext3 = req.getSession().getServletContext();

        System.out.println(servletContext1 == servletContext2);
        System.out.println(servletContext2 == servletContext3);

        //设置参数
        servletContext1.setAttribute("hello","world");

        //从web.xml <context-param> 获取参数
        String initParameter = servletContext1.getInitParameter("xml-key");
        System.out.println(initParameter);

        //获取某个资源的绝对路径
        String realPath = servletContext1.getRealPath("web.xml");
        System.out.println(realPath);

        //获取项目访问路径
        String contextPath = servletContext1.getContextPath();
        System.out.println(contextPath);
    }
}
