<%@ page import="com.bookstore.javabean.Order" %>
<%@ page import="com.bookstore.javabean.DBCon" %><%--
  Created by IntelliJ IDEA.
  User: Rikka
  Date: 2018/5/28
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>editOrder</title>
</head>
<body>
    <%
        int id=Integer.parseInt(request.getParameter("id"));
        Order order= DBCon.queryOrderById(id);
    %>
    <form action="/servlet/editOrderServlet" method="post">
        <input type="hidden" name="id" value="<%=order.getId()%>">
        订单号：<input type="text" value="<%=order.getOrderId()%>" name="orderId"><br>
        下单人：<input type="text" value="<%=order.getAccount()%>" name="Account"><br>
        地址：<input type="text" value="<%=order.getAddress()%>" name="address"><br>
        订单总价：<input type="text" value="<%=order.getTotalPrice()%>" name="totalPrice"><br>
        订单时间：<input type="text" value="<%=order.getTime()%>" name="time"><br>
        <input type="submit" value="删除">
        <a href="showOrder.jsp"><input type="button" value="返回"></a>
    </form>
</body>
</html>
