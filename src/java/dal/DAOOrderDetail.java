/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OrderDetail;

/**
 *
 * @author ToneVn
 */
public class DAOOrderDetail extends DBContext {

    public int addOrderDetail(int product_id, int order_id, int quantity, double price, double total) {
        int n = 0;
        String sql = "insert into OrderDetail(order_id,product_id,quantity,price,total) values (?,?,?,?,?)";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, order_id);
            pstm.setInt(2, product_id);
            pstm.setInt(3, quantity);
            pstm.setDouble(4, price);
            pstm.setDouble(5, total);
            n = pstm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public ArrayList<OrderDetail> getOderDetailByOrderID(String order_id) {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [swp_shopping_online].[dbo].[OrderDetail] where order_id = " + order_id;
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                OrderDetail orderdetail = new OrderDetail();
                orderdetail.setProduct_id(rs.getInt("product_id"));
                orderdetail.setQuantity(rs.getInt("quantity"));
                orderdetail.setPrice(rs.getDouble("price"));
                orderdetail.setTotal(rs.getDouble("total"));
                orderDetails.add(orderdetail);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderDetails;
    }
}
