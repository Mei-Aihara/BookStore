<%--
  Created by IntelliJ IDEA.
  User: Rikka
  Date: 2018/5/19
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        int id=Integer.parseInt(request.getParameter("id"));
    %>
    <input type="text" value=<%=id%>>
</body>
</html>
