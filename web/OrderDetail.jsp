<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.javabean.DBCon" %>
<%@ page import="com.bookstore.javabean.OrderDetail" %><%--
  Created by IntelliJ IDEA.
  User: Rikka
  Date: 2018/6/6
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div style="text-align: center">
        <table>
            <tr>
                <td>id</td>
                <td>订单号</td>
                <td>书本名</td>
                <td>总价</td>
                <td>书本ID</td>
                <td>数量</td>
            </tr>
            <%
                String orderId=request.getParameter("id");
                List list=DBCon.queryOrderDetail(orderId);
                for(int i=0;i<list.size();i++){
                    OrderDetail orderDetail=null;
                    orderDetail=(OrderDetail)list.get(i);
            %>
            <tr>
                <td><%=orderDetail.getId()%></td>
                <td><%=orderDetail.getOrderId()%></td>
                <td><%=orderDetail.getBookName()%></td>
                <td><%=orderDetail.getBookPrice()%></td>
                <td><%=orderDetail.getBookId()%></td>
                <td><%=orderDetail.getNum()%></td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>
