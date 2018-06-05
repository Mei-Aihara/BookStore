package com.bookstore.servlet;

import com.bookstore.javabean.DBCon;
import com.bookstore.javabean.ShopCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "editCountServlet")
public class editCountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        String count=request.getParameter("count");
        ShopCart shopCart=DBCon.querySCbyId(id);
        int currentcount=Integer.parseInt(shopCart.getNum());
        int price=Integer.parseInt(shopCart.getBookPrice());
        if(count.equals("-")){
            price=price/currentcount;
            currentcount--;
            price=price*currentcount;
        }else if (count.equals("+")){
            price=price/currentcount;
            currentcount++;
            price=price*currentcount;
        }
        if(DBCon.editSC(currentcount,shopCart,price)){
            response.sendRedirect("../shoppingCart.jsp");
        }else {
            response.sendRedirect("../Error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
