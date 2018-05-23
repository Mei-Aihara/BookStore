<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.javabean.DBCon" %>
<%@ page import="com.bookstore.javabean.BookInfo" %><%--
  Created by IntelliJ IDEA.
  User: Rikka
  Date: 2018/5/2
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookStore</title>
</head>
<body>
    <div style="text-align: center">
        <h1>浏览图书</h1><hr>
        <table>
            <tr>
                <td>书名</td>
                <td>单价</td>
                <td>出版社</td>
                <td></td>
            </tr>
            <%
                List list=DBCon.queryBookInfo();
                BookInfo bookInfo = null;
                for(int i=0;i<list.size();i++){
                    bookInfo=(BookInfo)list.get(i);
            %>
            <tr>
                <td><%=bookInfo.getBookName()%></td>
                <td><%=bookInfo.getBookPrice()%></td>
                <td><%=bookInfo.getPublishingId()%></td>
                <td>
                    <form action="">
                        <input type="button" value="购买">
                    </form>
                </td>
            <%
                }
            %>
            </tr>
        </table><hr>
        <a href="">查看购物车</a>
        <a href="">查看订单</a>
    </div>
</body>
</html>
