<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.javabean.DBCon" %>
<%@ page import="com.bookstore.javabean.ShopCart" %>
<%--
  Created by IntelliJ IDEA.
  User: Rikka
  Date: 2018/5/28
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div style="text-align: center">
        <h2>购物车</h2><hr>
        <table>
            <tr>
                <td>书名</td>
                <td>数量</td>
                <td>价格</td>
                <td></td>
            </tr>
            <%
                List list= DBCon.querySC();
                ShopCart shopCart=null;
                for(int i=0;i<list.size();i++){
                    shopCart=(ShopCart)list.get(i);
            %>
            <tr>
                <td><%=shopCart.getBookName()%></td>
                <td>
                    <form action="/servlet/editCountServlet" method="post">
                        <input value="<%=shopCart.getId()%>" name="id" type="hidden">
                        <input type="submit" name="count" value="-">
                        <%=shopCart.getNum()%>
                        <input type="submit" name="count" value="+">
                    </form>
                </td>
                <td><%=shopCart.getBookPrice()%></td>
                <td>
                    <form action="/servlet/deleteSCSerlvet" method="post">
                        <input type="hidden" value="<%=shopCart.getId()%>" name="id">
                        <input type="submit" value="删除">
                    </form>
                    <form action="" method="post">
                        <input type="hidden" value="<%=shopCart.getId()%>" name="id">
                        <input type="submit" value="购买">
                    </form>
                </td>
            </tr>
            <%
                }
                String name=String.valueOf(session.getAttribute("userName"));
                System.out.println(name);
            %>
        </table>
    </div>
</body>
</html>