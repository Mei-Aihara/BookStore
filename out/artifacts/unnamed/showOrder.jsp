<%@ page import="com.bookstore.javabean.DBCon" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.javabean.Order" %><%--
  Created by IntelliJ IDEA.
  User: Rikka
  Date: 2018/5/24
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showOrder</title>
</head>
<body>
    <div style="text-align: center">
        <h2>查看修改订单</h2><hr>
        <table>
            <tr>
                <td>ID</td>
                <td>订单号</td>
                <td>下单人</td>
                <td>下单时间</td>
                <td></td>
            </tr>
            <%
                List list=DBCon.queryOrder();
                Order order;
                for(int i=0;i<list.size();i++){
                    order=(Order)list.get(i);
            %>
            <tr>
                <td><%=order.getId()%></td>
                <td><%=order.getOrderId()%></td>
                <td><%=order.getAccount()%></td>
                <td><%=order.getTime()%></td>
                <td>
                    <form action="" method="post">
                        <input type="hidden" name="id" value="<%=order.getId()%>">
                        <input type="submit" value="修改">
                    </form>
                    <form action="deleteOrder.jsp" method="post">
                        <input type="hidden" name="id" value="<%=order.getId()%>">
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