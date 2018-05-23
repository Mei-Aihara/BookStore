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
import java.util.List;

@WebServlet(name = "showDetailServlet")
public class showDetailServlet extends HttpServlet {
    public showDetailServlet(){

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String bookId=session.getAttribute("BookId").toString();
        if(bookId==null||bookId.equals("")){
            bookId=request.getParameter("bookId");
        }
        String bookName=request.getParameter("bookName");
        String version=request.getParameter("version");
        String bookPrice=request.getParameter("bookPrice");
        String copyrightId=request.getParameter("copyrightId");
        String publishingId=request.getParameter("publishingId");
        List<BookInfo> bookInfoList=DBCon.queryBookInfo();
        session.setAttribute("detailInfo",bookInfoList);
        response.sendRedirect("showDetail.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
