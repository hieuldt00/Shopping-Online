/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;

/**
 *
 * @author ToneVn
 */
public class DAOOrder extends DBContext {
    public long createOrder(int account_id, String order_address, String order_name, String order_phone, double total, boolean status, int shop_id) {
        long n = 0;
        String sql = "insert into [Order](account_id,order_address,order_name,order_phone,total,status,shop_id) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, account_id);
            pstm.setString(2, order_address);
            pstm.setString(3, order_name);
            pstm.setString(4, order_phone);
            pstm.setDouble(5, total);
			pstm.setBoolean(6, false);
			pstm.setInt(7, shop_id);
            pstm.executeUpdate();
			try(ResultSet generatedKeys = pstm.getGeneratedKeys()){
				if (generatedKeys.next()) {
					n = generatedKeys.getLong(1);
				}
			}
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public String generateOrderID() {
        String code = "";
        Date date = new Date();
        long millisecond = date.getTime();
        code = millisecond + "";
        return code;
    }

    public ArrayList<Order> getOrderByAccountID(int account_id) {
        ArrayList<Order> Order = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [swp_shopping_online].[dbo].[Order] where account_id = " + account_id;
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getInt("Order_id"));
                order.setDateCreate(rs.getDate("dateCreate"));
                order.setOrder_name(rs.getString("order_name"));
                order.setOrder_phone(rs.getString("order_phone"));
                order.setOrder_address(rs.getString("order_address"));
                order.setTotal(rs.getDouble("total"));
                order.setStatus((rs.getString("status") == null ? -1 : rs.getInt("status") == 1 ? 1 : 0));
                order.setShop_id(rs.getInt("shop_id"));

                Order.add(order);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Order;
    }

    public void UpdateStatusOrder(int status, int order_id) {
        String s;
        if (status == -1) {
            s = null;
        } else {
            s = "" + status;
        }
        String sql = "update [Order] set status=" + s + " where order_id=" + order_id;
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DAOOrder daoCate = new DAOOrder();
        ArrayList<Order> categories = daoCate.getOrderByAccountID(1);
        for (Order category : categories) {
            System.out.println(category.toString());
        }
    }

}
