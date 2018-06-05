<%@ page import="com.bookstore.javabean.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.javabean.DBCon" %><%--
  Created by IntelliJ IDEA.
  User: Rikka
  Date: 2018/5/28
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CustomerOrder</title>
</head>
<body>
    <div style="text-align: center">
        <h2>查看个人订单</h2>
        <table>
            <tr>
                <td>ID</td>
                <td>订单号</td>
                <td>订单总额</td>
                <td>订单时间</td>
                <td></td>
            </tr>
            <%
                Order order=null;
                List<Order> list=DBCon.queryOrder();
                for(int i=0;i<list.size();i++){
                    order=list.get(i);
            %>
            <tr>
                <td><%=order.getId()%></td>
                <td><%=order.getOrderId()%></td>
                <td><%=order.getTotalPrice()%></td>
                <td><%=order.getTime()%></td>
                <td>
                    <form action="" method="post">
                        <input type="hidden" value="<%=order.getId()%>" name="id">
                        <input type="submit" value="查看详细">
                    </form>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>
