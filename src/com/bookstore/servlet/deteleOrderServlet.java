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

@WebServlet(name = "deteleOrderServlet")
public class deteleOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        PrintWriter out = response.getWriter();
        Order order=DBCon.queryOrderById(id);
        if(DBCon.deleteOrder(order)){
            out.println("<script type='text/javascript'>alert('订单删除成功！');</script>");
            response.sendRedirect("../showOrder.jsp");
        }else {
            response.sendRedirect("../deleteOrder.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
