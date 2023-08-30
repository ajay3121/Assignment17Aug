package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderDao {
    int order_id;
    private static String url = "jdbc:mysql://localhost:3306/ajay";
    public void displayOrderedProductById(int productId){
        try(Connection con = DriverManager.getConnection(url,"root","ajay@312");
            PreparedStatement pst = con.prepareStatement("select * from ordered_products"))
        {
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                if(productId == rs.getInt(1)){
                    order_id = rs.getInt(2);
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        try(Connection con = DriverManager.getConnection(url,"root","ajay@312");
            PreparedStatement pst = con.prepareStatement("select prod_name,prod_price from product where prod_id = ?"))
        {
            pst.setInt(1,productId);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                System.out.print(rs.getString(1)+" " +rs.getInt(2));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        try(Connection con = DriverManager.getConnection(url,"root","ajay@312");
            PreparedStatement pst = con.prepareStatement("select order_date,company_name from orders where order_id= ?"))
        {
            pst.setInt(1,order_id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                System.out.print(" " + rs.getString(1)+" "+ rs.getString(2));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println(" ");
    }
    public void displayOrderedProductsByOrderDate(String orderDate){
        try(Connection con = DriverManager.getConnection(url, "root", "ajay@312");
            PreparedStatement pst = con.prepareStatement("select  prod_name,prod_price,prod_desc,O.order_date from Product Pr, ordered_products Op, orders O where Pr.prod_id = Op.prod_id and Op.order_id = O.order_id and O.order_date = ?")){
            pst.setString(1,orderDate);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getString(3)+" "+rs.getString(4));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
