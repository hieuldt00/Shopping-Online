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
import model.Cart;

/**
 *
 * @author ToneVn
 */
public class DAOCart extends DBContext {

	//get carts by user id
	public ArrayList<Cart> getCartsByAccount_id(int account_id) {
		ArrayList<Cart> carts = null;
		String sql = "select * from Cart where account_id = ?";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, account_id);
			ResultSet rs = pstm.executeQuery();
			carts = new ArrayList<>();
			while (rs.next()) {
				Cart cart = new Cart();
				cart.setAccount_id(rs.getInt("account_id"));
				cart.setProduct_id(rs.getInt("product_id"));
				cart.setQuantity(rs.getInt("quantity"));
				carts.add(cart);
			}
			return carts;
		} catch (SQLException ex) {
			Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	//add product to Cart
	public int AddProductToCart(int account_id, int product_id, int quantity) {
		int n = 0;
		String sql = "insert into [Cart](account_id,product_id,quantity) values (?,?,?)";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, account_id);
			pstm.setInt(2, product_id);
			pstm.setInt(3, quantity);
			n = pstm.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
		}
		return n;
	}

	//update product quantity in Cart
	public int UpdateCart(int account_id, int product_id, int quantity) {
		int n = 0;
		String sql = "update [Cart] set quantity = ? where account_id = ? and product_id = ?";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, quantity);
			pstm.setInt(2, account_id);
			pstm.setInt(3, product_id);
			n = pstm.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
		}
		return n;
	}
	
	//Delete product from cart
	public int RemoveProductFromCart(int account_id, int product_id) {
		int n = 0;
		String sql = "delete from Cart where account_id = ? and product_id = ?";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, account_id);
			pstm.setInt(2, product_id);
			n = pstm.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
		}
		return n;
	}
	//Delete all cart
	public int RemoveAllProductFromCart(int account_id) {
		int n = 0;
		String sql = "delete from Cart where account_id = ?";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, account_id);
			n = pstm.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
		}
		return n;
	}
	

	public static void main(String[] args) {
		DAOCart daoCart = new DAOCart();
		ArrayList<Cart> carts = daoCart.getCartsByAccount_id(9);
		for (Cart cart : carts) {
			System.out.println(cart.toString());
		}
	}
}
