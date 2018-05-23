package com.bookstore.servlet;

import com.bookstore.javabean.BookInfo;
import com.bookstore.javabean.DBCon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "deleteBookServlet")
public class deleteBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String id=request.getParameter("id");
        System.out.print(id);
        BookInfo bookInfo=DBCon.queryBookbyId(Integer.valueOf(id));
        PrintWriter out= response.getWriter();
        if(DBCon.deleteBook(bookInfo)){
            out.println("<script language='javascript'>alert('书本删除成功！');</script>");
            response.sendRedirect("../showDetail.jsp");
        }else {
            response.sendRedirect("../Error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
