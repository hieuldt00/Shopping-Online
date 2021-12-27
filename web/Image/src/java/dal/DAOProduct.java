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
import model.Product;

/**
 *
 * @author ToneVn
 */
public class DAOProduct extends DBContext {
//	private int product_id;
//	private int product_name;
//	private int quantity;
//	private double price;
//	private String description;
//	private int status;
//	private int category_id;
//	private int shop_id;

	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> products = new ArrayList<>();
		try {
			String sql = "SELECT * FROM Product";
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProduct_id(rs.getInt("product_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setQuantity(rs.getInt("quantity"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setDetail(rs.getString("detail"));
				product.setStatus(rs.getInt("status"));
				product.setImage_thumbnail(rs.getString("image_thumbnail"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setShop_id(rs.getInt("shop_id"));
				products.add(product);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
		return products;
	}

	public Product getProductByProduct_id(int product_id) {
		Product product = new Product();
		String sql = "SELECT [product_id],[product_name],[quantity],[price],[description],[detail],[image_thumbnail],[status],[category_id],[shop_id] FROM [swp_shopping_online].[dbo].[Product] where product_id = " + product_id;
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				product.setProduct_id(product_id);
				product.setProduct_name(rs.getString("product_name"));
				product.setQuantity(rs.getInt("quantity"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setDetail(rs.getString("detail"));
				product.setStatus(rs.getInt("status"));				
				product.setImage_thumbnail(rs.getString("image_thumbnail"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setShop_id(rs.getInt("shop_id"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
		return product;
	}

	public ArrayList<Product> getProductsByCategory_id(int category_id) {
		ArrayList<Product> products = new ArrayList<>();
		String sql = "SELECT [product_id],[product_name],[quantity],[price],[description],[detail],[image_thumbnail],[status],[category_id],[shop_id] FROM [swp_shopping_online].[dbo].[Product] where category_id = " + category_id;
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProduct_id(rs.getInt("product_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setQuantity(rs.getInt("quantity"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setDetail(rs.getString("detail"));
				product.setImage_thumbnail(rs.getString("image_thumbnail"));
				product.setStatus(rs.getInt("status"));
				product.setCategory_id(category_id);
				product.setShop_id(rs.getInt("shop_id"));
				products.add(product);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
		return products;
	}

	public static void main(String[] args) {
		DAOProduct daoProduct = new DAOProduct();
//		ArrayList<Product> products = daoProduct.getAllProducts();
		ArrayList<Product> products = daoProduct.getProductsByCategory_id(19);
//		Product p = daoProduct.getProductByProduct_id(3);
//		System.out.println(p.toString());
		for (Product product : products) {
			System.out.println(product.toString());
		}
	}
}
