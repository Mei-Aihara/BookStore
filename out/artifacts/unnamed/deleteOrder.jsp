<%@ page import="com.bookstore.javabean.Order" %>
<%@ page import="com.bookstore.javabean.DBCon" %><%--
  Created by IntelliJ IDEA.
  User: Rikka
  Date: 2018/5/24
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function x() {
            if(confirm("确认删除？")){
                return true;
            }else {
                return false;
            }
        }
    </script>
</head>
<body>
    <%
        int id=Integer.parseInt(request.getParameter("id"));
        Order order= DBCon.queryOrderById(id);
    %>
    <form action="/servlet/deleteOrderServlet" method="post" onsubmit="x()">
        <input type="hidden" name="id" value="<%=order.getId()%>">
        订单号：<%=order.getOrderId()%><br>
        下单人：<%=order.getAccount()%><br>
        地址：<%=order.getAddress()%><br>
        订单总价：<%=order.getTotalPrice()%><br>
        订单时间：<%=order.getTime()%><br>
        <input type="submit" value="删除">
        <a href="showOrder.jsp"><input type="button" value="返回"></a>
    </form>
</body>
</html>
