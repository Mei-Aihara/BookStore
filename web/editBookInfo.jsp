<%@ page import="com.bookstore.javabean.BookInfo" %>
<%@ page import="com.bookstore.javabean.DBCon" %><%--
  Created by IntelliJ IDEA.
  User: Rikka
  Date: 2018/5/17
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    int id=Integer.valueOf(request.getParameter("id"));
    BookInfo bookInfo=DBCon.queryBookbyId(id);
%>
<form action="/servlet/editBookInfoServlet" method="get">
    <input type="hidden" value=<%=bookInfo.getId()%> name="id">
    图书名称：<input type="text" value="<%=bookInfo.getBookName()%>" name="bookName"><br>
    图书编号：<input type="text" value="<%=bookInfo.getBookId()%>" name="bookId"><br>
    图书单价：<input type="text" value="<%=bookInfo.getBookPrice()%>" name="bookPrice"><br>
    图书简介：<input type="text" value="<%=bookInfo.getBookIntro()%>" name="bookIntro"><br>
    版本：<input type="text" value="<%=bookInfo.getVersion()%>" name="version"><br>
    出版社：<input type="text" value="<%=bookInfo.getPublishingId()%>" name="publishingId"><br>
    版权：<input type="text" value="<%=bookInfo.getCopyrightId()%>" name="copyRightId"><br>
    <input type="submit" value="更新">
    <input type="reset" value="重置">
</form>
</body>
</html>
