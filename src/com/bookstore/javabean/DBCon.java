package com.bookstore.javabean;

import java.awt.print.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBCon {
    public static Connection getDBcon(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://qq.yimiaohome.top/book";
            Connection connection=DriverManager.getConnection(url,"book","book");
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String queryUser(String userName,String password){
        try{
            Connection connection=getDBcon();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM book.Account WHERE Account='"+userName+"' AND Password='"+password+"'");
            while(resultSet!=null && resultSet.next()){
                if(resultSet.getString(2)==null||resultSet.getString(3)==null){  //若查询为空返回错误
                    statement.close();
                    resultSet.close();
                    connection.close();
                    return "false";
                }else if (resultSet.getString(2).equals(userName)&&resultSet.getString(3).equals(password)){
                    String id=resultSet.getString(5);
                    statement.close();
                    resultSet.close();
                    connection.close();
                    return id;
                }else {
                    statement.close();
                    resultSet.close();
                    connection.close();
                    return "false";
                }
            }
            statement.close();
            resultSet.close();
            connection.close();
            return "false";
        } catch (SQLException e) {
            e.printStackTrace();
            return "false";
        }
    }

    public static boolean register(String userName,String password,String email){
        try{
            Connection connection=getDBcon();
            Statement statement=connection.createStatement();       //查询是否存在用户，避免用户重复注册
            ResultSet resultSet=statement.executeQuery("SELECT * FROM book.Account WHERE Account='"+userName+"'");
            while (resultSet!=null){
                resultSet.next();
                if (resultSet.getString(2).equals(userName)){
                    statement.close();
                    resultSet.close();
                    connection.close();
                    return false;
                }
            }
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO book.Account value (?,?,?,?)");
            preparedStatement.setString(2,userName);
            preparedStatement.setString(3,password);
            preparedStatement.setString(4,email);
            preparedStatement.setString(5,"customer");
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addBook(String bookName,String bookId,String version, String bookPrice, String copyrightId, String publishingId, String bookIntro){
        try{
            Connection connection=getDBcon();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO book.bookInfo value (?,?,?,?,?,?,?)");
            preparedStatement.setString(2,bookName);
            preparedStatement.setString(3,bookId);
            preparedStatement.setString(4,version);
            preparedStatement.setString(5,bookPrice);
            preparedStatement.setString(6,copyrightId);
            preparedStatement.setString(7,publishingId);
            preparedStatement.setString(8,bookIntro);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<BookInfo> queryBookInfo(){
        try{
            Connection connection=getDBcon();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM book.bookInfo");
            ArrayList<BookInfo> bookInfos=new ArrayList<BookInfo>();
            while(resultSet.next()){
                BookInfo bookInfo=new BookInfo();
                bookInfo.setId(Integer.parseInt(resultSet.getString(1)));
                bookInfo.setBookName(resultSet.getString(2));
                bookInfo.setBookId(resultSet.getString(3));
                bookInfo.setVersion(resultSet.getString(4));
                bookInfo.setBookPrice(resultSet.getString(5));
                bookInfo.setCopyrightId(resultSet.getString(6));
                bookInfo.setPublishingId(resultSet.getString(7));
                bookInfo.setBookIntro(resultSet.getString(8));
                bookInfos.add(bookInfo);
            }
            resultSet.close();
            statement.close();
            connection.close();
            return bookInfos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BookInfo queryBookbyId(int id){
        try{
            Connection connection=getDBcon();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM book.bookInfo WHERE id='"+id+"'");
            BookInfo bookInfo=new BookInfo();
            while(resultSet.next()){
                bookInfo.setId(Integer.parseInt(resultSet.getString(1)));
                bookInfo.setBookName(resultSet.getString(2));
                bookInfo.setBookId(resultSet.getString(3));
                bookInfo.setVersion(resultSet.getString(4));
                bookInfo.setBookPrice(resultSet.getString(5));
                bookInfo.setCopyrightId(resultSet.getString(6));
                bookInfo.setPublishingId(resultSet.getString(7));
                bookInfo.setBookIntro(resultSet.getString(8));
            }
            resultSet.close();
            statement.close();
            connection.close();
            return bookInfo;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean editBook(BookInfo bookInfo){
        try{
            Connection connection=getDBcon();
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE book.bookInfo SET bookName=?,bookId=?,version=?,bookPrice=?,copyRightId=?,publishingId=?,bookIntro=? where id=?");
            preparedStatement.setString(1, String.valueOf(bookInfo.getId()));
            preparedStatement.setString(2,bookInfo.getBookName());
            preparedStatement.setString(3,bookInfo.getBookId());
            preparedStatement.setString(4,bookInfo.getVersion());
            preparedStatement.setString(5,bookInfo.getBookPrice());
            preparedStatement.setString(6,bookInfo.getCopyrightId());
            preparedStatement.setString(7,bookInfo.getPublishingId());
            preparedStatement.setString(8,bookInfo.getBookIntro());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteBook(BookInfo bookInfo){
        try{
            Connection connection=getDBcon();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE from book where id=?");
            preparedStatement.setString(1,String.valueOf(bookInfo.getId()));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Order> queryOrder(){
        try{
            Connection connection=getDBcon();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM book.Order");
            ArrayList<Order> orders=new ArrayList<Order>();
            while(resultSet.next()){
                Order order=new Order();
                order.setId(Integer.parseInt(resultSet.getString(1)));
                order.setOrderId(resultSet.getString(2));
                order.setAccount(resultSet.getString(3));
                order.setAddress(resultSet.getString(5));
                order.setTotalPrice(resultSet.getString(4));
                order.setTime(resultSet.getString(6));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Order queryOrderById(int id){
        try{
            Connection connection=getDBcon();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM book.Order WHERE id="+id);
            Order order=new Order();
            while(resultSet.next()){
                order.setId(Integer.parseInt(resultSet.getString(1)));
                order.setOrderId(resultSet.getString(2));
                order.setAccount(resultSet.getString(3));
                order.setTotalPrice(resultSet.getString(4));
                order.setAddress(resultSet.getString(5));
                order.setTime(resultSet.getString(6));
            }
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean addOrder(Order order){
        try{
            Connection connection=getDBcon();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO book.Order value (?,?,?,?,?)");
            preparedStatement.setString(2,order.getOrderId());
            preparedStatement.setString(3,order.getAccount());
            preparedStatement.setString(4,order.getTotalPrice());
            preparedStatement.setString(5,order.getAddress());
            preparedStatement.setString(6,order.getTime());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deteleOrder(Order order){
        try{
            Connection connection=getDBcon();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE * FROM book.Order WHERE id=?");
            preparedStatement.setString(1,String.valueOf(order.getId()));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean editOrder(Order order){
        try{
            Connection connection=getDBcon();
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE book.Order SET orderId=?,Account=?,totalPrice=?,address=?,time=? WHERE id=?");
            preparedStatement.setString(1,String.valueOf(order.getId()));
            preparedStatement.setString(2,order.getOrderId());
            preparedStatement.setString(3,order.getAccount());
            preparedStatement.setString(4,order.getTotalPrice());
            preparedStatement.setString(5,order.getAddress());
            preparedStatement.setString(6,order.getTime());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
