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
<jsp:useBean id="time" class="java.util.Date"></jsp:useBean>
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
                String Account=request.getParameter("account");
                String id=request.getParameter("id");
                if(count.equals("0")){
                    ShopCart shopCart=DBCon.querySCbyId(id);
            %>
            <tr>
                <td><%=shopCart.getBookName()%></td>
                <td><%=shopCart.getNum()%></td>
                <td><%=shopCart.getBookPrice()%></td>
            </tr>
            <%
                }else if(count.equals("1")){
                    List list= DBCon.querySCbyAccount(Account);
                    ShopCart shopCart=null;
                    for(int i=0;i<list.size();i++){
                        shopCart=(ShopCart)list.get(i);
            %>
            <tr>
                <td><%=shopCart.getBookName()%></td>
                <td><%=shopCart.getNum()%></td>
                <td><%=shopCart.getBookPrice()%></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        <%
            if(count.equals("0")){
                ShopCart shopCart=DBCon.querySCbyId(id);
                String price=shopCart.getBookPrice();
        %>
        <form action="/servlet/addOrderServlet" method="post">
            <input type="hidden" value="<%=count%>" name="if">
            <input type="hidden" value="<%=id%>" name="id">
            <input type="hidden" value="<%=Account%>" name="account">
            <input type="hidden" value="<%=time%>" name="time">
            <input type="hidden" value="<%=price%>" name="totalPrice">
            地址：<input type="text" name="address"><br>
            <input type="submit" value="购买！">
        </form>
        <%
            }else if(count.equals("1")){
                List list=DBCon.querySCbyAccount(Account);
                int price=0;
                ShopCart shopCart=null;
                for(int i=0;i<list.size();i++){
                    shopCart=(ShopCart)list.get(i);
                    price=Integer.parseInt(shopCart.getBookPrice())+price;
                }
        %>
        <form action="/servlet/addOrderServlet" method="post">
            <input type="hidden" value="<%=count%>" name="if">
            <input type="hidden" value="<%=Account%>" name="account">
            <input type="hidden" value="<%=time%>" name="time">
            <input type="hidden" value="<%=price%>" name="totalPrice">
            地址：<input type="text" name="address"><br>
            <input type="submit" value="购买！">
        </form>
        <%
            }
        %>
    </div>
</body>
</html>
