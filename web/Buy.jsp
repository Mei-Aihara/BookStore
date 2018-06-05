<%@ page import="com.bookstore.javabean.BookInfo" %>
<%@ page import="com.bookstore.javabean.DBCon" %>
<%@ page import="com.bookstore.javabean.ShopCart" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rikka
  Date: 2018/6/6
  Time: 1:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div style="text-align: center">
        <h2>结账</h2><hr>
        <table>
            <tr>
                <td>书名</td>
                <td>数量</td>
                <td>价格</td>
                <td></td>
            </tr>
            <%
                String count=request.getParameter("if");
                if(count.equals("0")){
                    String id=request.getParameter("id");
                    ShopCart shopCart=DBCon.querySCbyId(id);
            %>
            <td><%=shopCart.getBookName()%></td>
            <td><%=shopCart.getNum()%></td>
            <td><%=shopCart.getBookPrice()%></td>
            <%
                }else if(count.equals("1")){
                    String Account=request.getParameter("account");
                    List list= DBCon.querySCbyAccount(Account);
                    ShopCart shopCart=null;
                    for(int i=0;i<list.size();i++){
                        shopCart=(ShopCart)list.get(i);
            %>
            <td><%=shopCart.getBookName()%></td>
            <td><%=shopCart.getNum()%></td>
            <td><%=shopCart.getBookPrice()%></td>
            <%
                    }
                }
            %>
        </table>
        <jsp:useBean id="time" class="java.util.Date"></jsp:useBean>
        <form action="" method="post">
            <input type="hidden" value="<%=time%>" name="time">
            <input type="hidden" value="1" name="orderId">
            <input type="hidden" value="<%=%>" name="totalPrice">
            地址：<input type="text" name="address"><br>
        </form>
    </div>
</body>
</html>
