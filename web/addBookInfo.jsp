<%--
  Created by IntelliJ IDEA.
  User: Rikka
  Date: 2018/5/9
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddBookInfo</title>
</head>
<body>
<div style="text-align: center">
    <form action="/servlet/addBookServlet">
        图书名称：<input type="text" name="bookName"><br>
        书号：<input type="text" name="bookId"><br>
        版本号：<input type="text" name="version"><br>
        图书单价：<input type="text" name="bookPrice"><br>
        版权号：<input type="text" name="copyrightId"><br>
        出版社：<input type="text" name="publishingId"><br>
        内容简介：<textarea cols="30" rows="5" name="bookIntro"></textarea><br>
        <input type="submit" value="提交">
        <input type="reset" value="重置">
    </form>
</div>
</body>
</html>
