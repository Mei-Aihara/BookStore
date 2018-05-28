<%@ page import="com.bookstore.javabean.BookInfo" %>
<%@ page import="com.bookstore.javabean.DBCon" %><%--
  Created by IntelliJ IDEA.
  User: Rikka
  Date: 2018/5/28
  Time: 21:41
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
        BookInfo bookInfo=DBCon.queryBookbyId(id);
    %>
    <form action="" method="post">
        <input type="hidden" name="id" value="<%=bookInfo.getId()%>">
        图书名称：<%=bookInfo.getBookName()%><br>
        图书编号：<%=bookInfo.getBookId()%><br>
        图书单价：<%=bookInfo.getBookPrice()%><br>
        图书简介：<%=bookInfo.getBookIntro()%><br>
        版本：<%=bookInfo.getVersion()%><br>
        出版社：<%=bookInfo.getPublishingId()%><br>
        版权：<%=bookInfo.getCopyrightId()%><br>
        <input type="submit" value="添加购物车">
    </form>
    <form action="" method="post">
        <input type="hidden" name="id" value="<%=bookInfo.getId()%>">
        <input type="submit" value="立即购买">
    </form>
    <a href="showDetail.jsp"><input type="button" value="返回"></a>
</body>
</html>
