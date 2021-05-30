<%--
  Created by IntelliJ IDEA.
  User: snf
  Date: 2021/5/29
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <form action="response" method="get">
        用户名:<input type="text" name="userName" value="" /><br>
        密码：<input type="password" name="password" value="" /><br>
        爱好：<br>
        <input type="checkbox" name="fav" value="1"/>java
        <input type="checkbox" name="fav" value="2"/>python
        <input type="checkbox" name="fav" value="3"/>php<br>
        <input type="submit" value="登录">
    </form>
</head>
<body>

</body>
</html>
