package com.bookstore.servlet;

import com.bookstore.javabean.DBCon;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public class loginServlet extends javax.servlet.http.HttpServlet {
    public loginServlet(){

    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
            String userName=request.getParameter("userName");       //获得Login.jsp中的userName和password的数据
            String password=request.getParameter("password");
            String code=request.getParameter("code");
            if(userName!=null&&password!=null){
                userName=new String(userName.getBytes("ISO-8859-1"));       //解决乱码问题
                String statement=DBCon.queryUser(userName,password);
                if(!statement.equals("false")){     //验证用户名和密码
                    HttpSession session=request.getSession();
                    session.setAttribute("userName",userName);     //利用session保存用户名和密码传递到下个页面
                    session.setAttribute("pwd",password);
                    String kaptcha=(String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);     //获得验证码
                    System.out.print(statement);
                    if(statement.equals("admin")&&code.equals(kaptcha)){        //用户输入的验证码和系统提供的验证码进行对比
                        response.sendRedirect("../AdminMain.jsp");
                    }else {
                        response.sendRedirect("../CustomerMain.jsp");
                    }
                }else {
                    response.sendRedirect("../Error.jsp");      //如果验证不成功转到错误页面
                }
            }else {
                response.sendRedirect("../Error.jsp");
            }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doPost(request,response);
    }
}
