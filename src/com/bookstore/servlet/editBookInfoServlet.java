package com.bookstore.servlet;

import com.bookstore.javabean.BookInfo;
import com.bookstore.javabean.DBCon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "editBookInfoServlet")
public class editBookInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.valueOf(request.getParameter("id"));
        BookInfo bookInfo= DBCon.queryBookbyId(id);
        PrintWriter out= response.getWriter();
        if(DBCon.editBook(bookInfo)){
            out.println("<script language='javascript'>alert('书本修改成功！');</script>");
            response.sendRedirect("../showDetail.jsp");
        }else {
            response.sendRedirect("../editBookInfo.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
