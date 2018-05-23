package com.bookstore.servlet;

import com.bookstore.javabean.DBCon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addBookServlet")
public class addBookServlet extends HttpServlet {
    public addBookServlet(){

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookName=request.getParameter("bookName");
        String bookId=request.getParameter("bookId");
        String version=request.getParameter("version");
        String bookPrice=request.getParameter("bookPrice");
        String copyrightId=request.getParameter("copyrightId");
        String publishingId=request.getParameter("publishingId");
        String bookIntro=request.getParameter("bookIntro");
        if(DBCon.addBook(bookName,bookId,version,bookPrice,copyrightId,publishingId,bookIntro)){
            response.setContentType("text/html,charset=utf-8");
            PrintWriter out=response.getWriter();
            out.print("<script language='javascript'>alert('书本添加成功！');</script>");
            HttpSession session=request.getSession();
            session.setAttribute("BookId",bookId);
            response.sendRedirect("../showDetail.jsp");
        }else{
            response.setContentType("text/html,charset=utf-8");
            PrintWriter out=response.getWriter();
            out.print("<script language='javascript'>alert('书本添加失败！');window.location.href='addBookInfo.jsp';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
