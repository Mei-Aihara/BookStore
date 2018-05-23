<%--
  Created by IntelliJ IDEA.
  User: Rikka
  Date: 2018/5/2
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <div style="text-align: center">
        <form action="/servlet/regServlet" method="Post">
            用户名：<input type="text" name="userName"><br>
            密码：<input type="password" name="password"><br>
            邮箱：<input type="text" name="email"><br>
            <input type="submit" value="注册">
            <input type="reset" value="重置">
        </form>
    </div>
</body>
</html>
