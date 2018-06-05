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
            while (resultSet!=null&&resultSet.next()){
                if (resultSet.getString(2).equals(userName)){
                    statement.close();
                    resultSet.close();
                    connection.close();
                    return false;
                }
            }
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO book.Account (Account,Password,Email,Identity) value (?,?,?,?)");
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,"customer");
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
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO book.bookInfo (bookName,bookId,version,bookPrice,copyrightId,publishId,bookIntro) value (?,?,?,?,?,?,?)");
            preparedStatement.setString(1,bookName);
            preparedStatement.setString(2,bookId);
            preparedStatement.setString(3,version);
            preparedStatement.setString(4,bookPrice);
            preparedStatement.setString(5,copyrightId);
            preparedStatement.setString(6,publishingId);
            preparedStatement.setString(7,bookIntro);
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
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE book.bookInfo SET bookName=?,bookId=?,version=?,bookPrice=?,copyrightId=?,publishId=?,bookIntro=? where id=?");
            preparedStatement.setString(8, String.valueOf(bookInfo.getId()));
            preparedStatement.setString(1,bookInfo.getBookName());
            preparedStatement.setString(2,bookInfo.getBookId());
            preparedStatement.setString(3,bookInfo.getVersion());
            preparedStatement.setString(4,bookInfo.getBookPrice());
            preparedStatement.setString(5,bookInfo.getCopyrightId());
            preparedStatement.setString(6,bookInfo.getPublishingId());
            preparedStatement.setString(7,bookInfo.getBookIntro());
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
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE from book.bookInfo where id=?");
            preparedStatement.setString(1,String.valueOf(bookInfo.getId()));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkOrderId(String orderId){
        try{
            Connection connection=getDBcon();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM book.Order WHERE orderId='"+orderId+"'");
            while(resultSet.next()&&resultSet!=null){
                if(resultSet.getString(2)==null){
                    return true;
                }else {
                    return false;
                }
            }
            return false;
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
            ResultSet resultSet=statement.executeQuery("SELECT * FROM book.Order WHERE id='"+id+"'");
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
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO book.Order (orderId,Account,totalPrice,address,time) value (?,?,?,?,?)");
            preparedStatement.setString(1,order.getOrderId());
            preparedStatement.setString(2,order.getAccount());
            preparedStatement.setString(3,order.getTotalPrice());
            preparedStatement.setString(4,order.getAddress());
            preparedStatement.setString(5,order.getTime());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteOrder(Order order){
        try{
            Connection connection=getDBcon();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM book.Order WHERE id=?");
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
            preparedStatement.setString(6,String.valueOf(order.getId()));
            preparedStatement.setString(1,order.getOrderId());
            preparedStatement.setString(2,order.getAccount());
            preparedStatement.setString(3,order.getTotalPrice());
            preparedStatement.setString(4,order.getAddress());
            preparedStatement.setString(5,order.getTime());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addSC(BookInfo bookInfo,String Account){
        try{
            Connection connection=getDBcon();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO book.shopCart (bookName,Account,bookId,bookPrice,num) value (?,?,?,?,?)");
            preparedStatement.setString(1,bookInfo.getBookName());
            preparedStatement.setString(2,Account);
            preparedStatement.setString(3,bookInfo.getBookId());
            preparedStatement.setString(4,bookInfo.getBookPrice());
            preparedStatement.setString(5,"1");
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<ShopCart> querySC(){
        try{
            Connection connection=getDBcon();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM book.shopCart");
            ArrayList<ShopCart> shopCarts=new ArrayList<ShopCart>();
            while(resultSet.next()){
                ShopCart shopCart=new ShopCart();
                shopCart.setBookName(resultSet.getString(2));
                shopCart.setAccount(resultSet.getString(3));
                shopCart.setBookId(resultSet.getString(4));
                shopCart.setId(Integer.parseInt(resultSet.getString(1)));
                shopCart.setBookPrice(resultSet.getString(5));
                shopCart.setNum(resultSet.getString(6));
                shopCarts.add(shopCart);
            }
            statement.close();
            resultSet.close();
            connection.close();
            return shopCarts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean editSC(int count,ShopCart shopCart,int price){
        try{
            Connection connection=getDBcon();
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE book.shopCart SET num=?,bookPrice=? WHERE id=?");
            preparedStatement.setString(1,String.valueOf(count));
            preparedStatement.setString(2,String.valueOf(price));
            preparedStatement.setString(3,String.valueOf(shopCart.getId()));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ShopCart querySCbyId(String id){
        try{
            Connection connection=getDBcon();
            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM book.shopCart WHERE id='"+id+"'");
            ShopCart shopCart=new ShopCart();
            while (resultSet.next()){
                shopCart.setNum(resultSet.getString(6));
                shopCart.setBookPrice(resultSet.getString(5));
                shopCart.setAccount(resultSet.getString(3));
                shopCart.setBookId(resultSet.getString(4));
                shopCart.setId(Integer.parseInt(resultSet.getString(1)));
                shopCart.setBookPrice(resultSet.getString(2));
            }
            statement.close();
            resultSet.close();
            connection.close();
            return shopCart;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteSC(ShopCart shopCart){
        try{
            Connection connection=getDBcon();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM book.shopCart WHERE id=?");
            preparedStatement.setString(1,String.valueOf(shopCart.getId()));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<ShopCart> querySCbyAccount(String Account){
        try{
            Connection connection=getDBcon();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM book.shopCart WHERE Account='"+Account+"'");
            ArrayList<ShopCart> shopCarts=new ArrayList<ShopCart>();
            while(resultSet.next()){
                ShopCart shopCart=new ShopCart();
                shopCart.setBookName(resultSet.getString(2));
                shopCart.setAccount(resultSet.getString(3));
                shopCart.setBookId(resultSet.getString(4));
                shopCart.setId(Integer.parseInt(resultSet.getString(1)));
                shopCart.setBookPrice(resultSet.getString(5));
                shopCart.setNum(resultSet.getString(6));
                shopCarts.add(shopCart);
            }
            statement.close();
            resultSet.close();
            connection.close();
            return shopCarts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
