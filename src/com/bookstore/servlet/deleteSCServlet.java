package com.bookstore.servlet;

import com.bookstore.javabean.DBCon;
import com.bookstore.javabean.ShopCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "deleteSCServlet")
public class deleteSCServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        PrintWriter out = response.getWriter();
        ShopCart shopCart=DBCon.querySCbyId(id);
        if(DBCon.deleteSC(shopCart)){
            out.println("<script type='text/javascript'>alert('订单删除成功！');</script>");
            response.sendRedirect("../shoppingCart.jsp");
        }else {
            response.sendRedirect("../Error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
