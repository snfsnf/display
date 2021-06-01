<%--
  Created by IntelliJ IDEA.
  User: snf
  Date: 2021/5/30
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,java.util.Arrays" pageEncoding="utf-8" %>
<%@ page errorPage="error.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%!
    String str = "jsp全局变量";
    public void test(){
        System.out.println("全局代码块");
    }
%>
<%
//int i = 5/0;
%>
<%--获取全局变量值并响应到页面--%>
<%=str%>

<%--静态导入页面--%>
<%@ include file="index.jsp"%>
<%--动态导入页面--%>
<jsp:include page=""/>
</body>

</html>
