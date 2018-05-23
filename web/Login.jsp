<%--
  Created by IntelliJ IDEA.
  User: Rikka
  Date: 2018/5/2
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div style="text-align: center">
    <form action="servlet/loginServlet" method="post">
        账户：<input type="text" name="userName"><br>
        密码：<input type="password" name="password"><br>
        验证码：<input type="text" name="code"><img src="/Kaptcha.jpg"><br>
        <input type="submit" value="登录">
        <input type="reset" value="重置">
    </form>
    <hr>
    <a href="Register.jsp">注册新用户</a>
</div>
</body>
</html>
