<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.javabean.DBCon" %>
<%@ page import="com.bookstore.javabean.BookInfo" %><%--
  Created by IntelliJ IDEA.
  User: Rikka
  Date: 2018/5/9
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ShowBookInfomationDetail</title>
</head>
<body>
    <div style="text-align: center">
        <h1>查看修改书籍</h1><hr>
        <table>
            <tr>
                <td>ID</td>
                <td>书名</td>
                <td>出版社</td>
                <td>版权号</td>
                <td></td>
            </tr>
            <%
                List list=DBCon.queryBookInfo();
                BookInfo bookInfo;
                for(int i=0;i<list.size();i++){
                    bookInfo=(BookInfo)list.get(i);
            %>
            <tr>
                <td><%=bookInfo.getId()%></td>
                <td><%=bookInfo.getBookName()%></td>
                <td><%=bookInfo.getPublishingId()%></td>
                <td><%=bookInfo.getCopyrightId()%></td>
                <td>
                    <form action="editBookInfo.jsp">
                        <input type="hidden" value="<%=bookInfo.getId()%>" name="id">
                        <input type="submit" value="修改">
                    </form>
                    <form action="delete.jsp" method="get">
                        <input type="hidden" name="id" value="<%=bookInfo.getId()%>">
                        <input type="submit" value="删除">
                    </form>
                </td>
            <%
                }
            %>
            </tr>
        </table>
    </div>
</body>
</html>
