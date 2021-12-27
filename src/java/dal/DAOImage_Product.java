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
import model.Image_Product;

/**
 *
 * @author ToneVn
 */
public class DAOImage_Product extends DBContext{
	public ArrayList<Image_Product> getAllImage_Products() {
		ArrayList<Image_Product> image_products = new ArrayList<>();
		try {
			String sql = "SELECT * FROM Image_Product";
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Image_Product image_product = new Image_Product();
				image_product.setUrl(rs.getString("url"));
				image_product.setProduct_id(rs.getInt("product_id"));
				image_product.setStatus(rs.getInt("status"));
				image_products.add(image_product);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOImage_Product.class.getName()).log(Level.SEVERE, null, ex);
		}
		return image_products;
	}
	public ArrayList<Image_Product> getAllImage_ProductsByProductID(int product_id) {
		ArrayList<Image_Product> image_products = new ArrayList<>();
		try {
			String sql = "SELECT * FROM Image_Product where product_id =" + product_id;
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Image_Product image_product = new Image_Product();
				image_product.setUrl(rs.getString("url"));
				image_product.setProduct_id(rs.getInt("product_id"));
				image_product.setStatus(rs.getInt("status"));
				image_products.add(image_product);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOImage_Product.class.getName()).log(Level.SEVERE, null, ex);
		}
		return image_products;
	}
}
