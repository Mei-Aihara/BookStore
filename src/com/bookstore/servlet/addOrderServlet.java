package com.bookstore.servlet;

import com.bookstore.javabean.Account;
import com.bookstore.javabean.DBCon;
import com.bookstore.javabean.Order;
import com.bookstore.javabean.ShopCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "addOrderServlet")
public class addOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId=1;
        String id=request.getParameter("id");
        String count=request.getParameter("if");
        while(DBCon.checkOrderId(orderId)){
            orderId++;
        }
        Order order=new Order();
        order.setAccount(request.getParameter("account"));
        order.setAddress(request.getParameter("address"));
        order.setTotalPrice(request.getParameter("totalPrice"));
        order.setTime(request.getParameter("time"));
        order.setOrderId(orderId);
        if(DBCon.addOrder(order)){
            if(count.equals("0")){
                ShopCart shopCart= DBCon.querySCbyId(id);
                if(DBCon.addOrderDetail(orderId,shopCart)){
                    DBCon.deleteSC(shopCart);
                    response.sendRedirect("../Success.jsp");
                }else {
                    response.sendRedirect("../Error.jsp");
                }
            }else if(count.equals("1")){
                List list=DBCon.querySCbyAccount(order.getAccount());
                for(int i=0;i<list.size();i++){
                    ShopCart shopCart=(ShopCart)list.get(i);
                    if(DBCon.addOrderDetail(orderId,shopCart)) {
                        DBCon.deleteSC(shopCart);
                        continue;
                    } else {
                        response.sendRedirect("../Error.jsp");
                    }
                }
                response.sendRedirect("../Success.jsp");
            }
        }else {
            response.sendRedirect("../Error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
