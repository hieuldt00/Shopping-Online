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
import model.Category;

/**
 *
 * @author ToneVn
 */
public class DAOCategory extends DBContext{
//	private int category_id;
//	private String category_name;
//	private int status;
	public ArrayList<Category> getAllCategories() {
		ArrayList<Category> categories = new ArrayList<>();
		try {
			String sql = "SELECT * FROM Category";
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCategory_id(rs.getInt("category_id"));
				category.setCategory_name(rs.getString("category_name"));
				category.setCategory_image(rs.getString("category_image"));
				category.setStatus(rs.getInt("status"));
				categories.add(category);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
		}
		return categories;
	}
	public Category getCategoryByProduct_id(int product_id) {
		Category category = new Category();
		String sql = "select Category.* from Category inner join Product on Category.category_id = Product.category_id where product_id = " + product_id;

		PreparedStatement pstm;
		try {
			pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				category.setCategory_id(rs.getInt("category_id"));
				category.setCategory_name(rs.getString("category_name"));
				category.setCategory_image(rs.getString("category_image"));
				category.setStatus(rs.getInt("status"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
		}
		return category;
	}
	public Category getCategoryByCategory_id(int category_id) {
		Category category = new Category();
		String sql = "SELECT * FROM Category where category_id =" + category_id;
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				category.setCategory_id(category_id);
				category.setCategory_name(rs.getString("category_name"));
				category.setCategory_image(rs.getString("category_image"));
				category.setStatus(rs.getInt("status"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
		}
		return category;
	}
	public static void main(String[] args) {
		DAOCategory daoCate = new DAOCategory();
		ArrayList<Category> categories = daoCate.getAllCategories();
		for (Category category : categories) {
			System.out.println(category.toString());
		}
	}
}
