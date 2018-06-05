package com.bookstore.servlet;

import com.bookstore.javabean.BookInfo;
import com.bookstore.javabean.DBCon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addSCServlet")
public class addSCServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Account=request.getParameter("user");
        int id=Integer.parseInt(request.getParameter("id"));
        BookInfo bookInfo=DBCon.queryBookbyId(id);
        if(DBCon.addSC(bookInfo,Account)){
            response.sendRedirect("../shoppingCart.jsp");
        }else {
            response.sendRedirect("../Error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
