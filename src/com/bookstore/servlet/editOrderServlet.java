package com.bookstore.servlet;

import com.bookstore.javabean.DBCon;
import com.bookstore.javabean.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "editOrderServlet")
public class editOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = new Order();
        int id = Integer.parseInt(request.getParameter("id"));
        order.setId(id);
        order.setTime(request.getParameter("time"));
        order.setTotalPrice(request.getParameter("totalPrice"));
        order.setAddress(request.getParameter("address"));
        order.setOrderId(Integer.parseInt(request.getParameter("orderId")));
        order.setAccount(request.getParameter("Account"));
        PrintWriter out = response.getWriter();
        if (DBCon.editOrder(order)) {
            out.println("<script language='javascript'>alert('订单修改成功！');</script>");
            response.sendRedirect("../showOrder.jsp");
        } else {
            response.sendRedirect("../editOrder.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
