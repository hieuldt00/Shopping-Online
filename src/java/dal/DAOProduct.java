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

	public int[] getMinMaxPrice() {
		int[] arr = new int[2];
		try {
			PreparedStatement pstm = connection.prepareStatement("SELECT MIN(price) FROM Product");
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				arr[0] = rs.getInt(1);
			}
			pstm = connection.prepareStatement("SELECT MAX(price) FROM Product");
			rs = pstm.executeQuery();
			while (rs.next()) {
				arr[1] = rs.getInt(1);
			}

		} catch (SQLException e) {
		}
		return arr;
	}

	public ArrayList<Product> PaggingSearchProduct(String name, int[] cate, int index, int size, String sortby)
			throws SQLException {

		ArrayList<Product> products = new ArrayList<>();
		String sql = "";
		ResultSet rs = null;
		if (cate != null) {
			sql = "with x as (select RoW_Number() over (order by price " + sortby
					+ " ) as r,* from Product where product_name like ? and(";
			if (cate.length == 1) {
				sql += "category_id=" + cate[0] + ")) select * from x where r between ?*" + size + "- (" + size
						+ "-1) and ?*" + size + "";
			} else {
				for (int i = 0; i < cate.length - 1; i++) {
					sql += "category_id=" + cate[i] + " or ";
				}
				sql += "category_id=" + cate[cate.length - 1] + ")) select * from x where r between " + index + "*"
						+ size + "- (" + size + "-1) and " + index + "*" + size + "";
			}

			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, "%" + name + "%");

			rs = pstm.executeQuery();
		} else {
			sql = "with x as (select RoW_Number() over (order by price " + sortby
					+ " ) as r,* from Product where product_name like ?)\n" + "select * from x where r between " + index
					+ "*" + size + "- (" + size + "-1) and " + index + "*" + size + "";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, "%" + name + "%");

			rs = pstm.executeQuery();
		}
		while (rs.next()) {
			Product product = new Product();
			product.setProduct_id(rs.getInt("product_id"));
			product.setProduct_name(rs.getString("product_name"));
			product.setQuantity(rs.getInt("quantity"));
			product.setPrice(rs.getDouble("price"));
			product.setDescription(rs.getString("description"));
			product.setStatus(rs.getInt("status"));
			product.setCategory_id(rs.getInt("category_id"));
			product.setShop_id(rs.getInt("shop_id"));
			products.add(product);
		}
		return products;
	}

	public int countProduct(int[] cate, String name) {
		String sql = "";

		ResultSet rs = null;
		try {
			if (cate != null) {
				sql = "SELECT COUNT (*) FROM Product where product_name like ? and (";
				if (cate.length == 1) {
					sql += " category_id=" + cate[0] + ")";
				} else {
					for (int i = 0; i < cate.length - 1; i++) {
						sql += " category_id=" + cate[i] + " or ";
					}
					sql += " category_id=" + cate[cate.length - 1] + ")";
				}
				PreparedStatement pstm = connection.prepareStatement(sql);
				pstm.setString(1, "%" + name + "%");

				rs = pstm.executeQuery();
			} else {
				sql = "SELECT COUNT (*) FROM Product where product_name like ?";
				PreparedStatement pstm = connection.prepareStatement(sql);
				pstm.setString(1, "%" + name + "%");
				rs = pstm.executeQuery();
			}
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;
	}

	public ArrayList<Product> PaggingSearchbyPrice(String name, int[] cate, int from, int to, int index, int size,
			String sortby) throws SQLException {
		ArrayList<Product> products = new ArrayList<>();
		String sql = "";

		ResultSet rs = null;
		if (name.equals("") && cate == null) {
			sql = "with x as (select RoW_Number() over (order by price " + sortby
					+ " ) as r,* from Product where price between ? and ?)\n" + "select * from x where r between "
					+ index + "*" + size + "- (" + size + "-1) and " + index + "*" + size + "";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, from);
			pstm.setInt(2, to);

			rs = pstm.executeQuery();
		} else if (cate == null) {
			sql = "with x as (select RoW_Number() over (order by price " + sortby
					+ " ) as r,* from Product where product_name like ?  and price between ? and ?)\n"
					+ "select * from x where r between " + index + "*" + size + "- (" + size + "-1) and " + index + "*"
					+ size + "";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, "%" + name + "%");
			pstm.setInt(2, from);
			pstm.setInt(3, to);

			rs = pstm.executeQuery();
		} else if (name.equals("")) {
			sql = "with x as (select RoW_Number() over (order by price " + sortby
					+ " ) as r,* from Product where price between ? and ? and (";
			if (cate.length == 1) {
				sql += "category_id=" + cate[0] + ")) select * from x where r between ?*" + size + "- (" + size
						+ "-1) and ?*" + size + "";
			} else {
				for (int i = 0; i < cate.length - 1; i++) {
					sql += "category_id=" + cate[i] + " or ";
				}
				sql += " category_id=" + cate[cate.length - 1] + ")) select * from x where r between " + index + "*"
						+ size + "- (" + size + "-1) and " + index + "*" + size + "";
			}

			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, from);
			pstm.setInt(2, to);

			rs = pstm.executeQuery();
		} else {
			sql = "with x as (select RoW_Number() over (order by price " + sortby
					+ " ) as r,* from Product where product_name like ? and price between ? and ? and (";
			if (cate.length == 1) {
				sql += " category_id=" + cate[0] + ")) select * from x where r between " + index + "*" + size + "- ("
						+ size + "-1) and " + index + "*" + size + "";
			} else {
				for (int i = 0; i < cate.length - 1; i++) {
					sql += " category_id=" + cate[i] + " or ";
				}
				sql += " category_id=" + cate[cate.length - 1] + ")) select * from x where r between " + index + "*"
						+ size + "- (" + size + "-1) and " + index + "*" + size + "";
			}

			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, "%" + name + "%");
			pstm.setInt(2, from);
			pstm.setInt(3, to);

			rs = pstm.executeQuery();
		}

		while (rs.next()) {
			Product product = new Product();
			product.setProduct_id(rs.getInt("product_id"));
			product.setProduct_name(rs.getString("product_name"));
			product.setQuantity(rs.getInt("quantity"));
			product.setPrice(rs.getDouble("price"));
			product.setDescription(rs.getString("description"));
			product.setStatus(rs.getInt("status"));
			product.setCategory_id(rs.getInt("category_id"));
			product.setShop_id(rs.getInt("shop_id"));
			products.add(product);
		}
		return products;
	}

	public int countProductByPrice(String name, int[] cate, int from, int to) {
		String sql = "";

		ResultSet rs = null;
		try {
			if (name.equals("") && cate == null) {
				sql = "SELECT COUNT (*) FROM Product where price between ? and ? ";
				PreparedStatement pstm = connection.prepareStatement(sql);
				pstm.setInt(1, from);
				pstm.setInt(2, to);
				rs = pstm.executeQuery();
			} else if (cate == null) {
				sql = "SELECT COUNT (*) FROM Product where product_name like ?  and price between ? and ? ";
				PreparedStatement pstm = connection.prepareStatement(sql);
				pstm.setString(1, "%" + name + "%");
				pstm.setInt(2, from);
				pstm.setInt(3, to);
				rs = pstm.executeQuery();
			} else if (name.equals("")) {
				sql = "SELECT COUNT (*) FROM Product where price between ? and ? \n" + "and ( ";
				if (cate.length == 1) {
					sql += " category_id=" + cate[0] + ")";
				} else {
					for (int i = 0; i < cate.length - 1; i++) {
						sql += " category_id=" + cate[i] + " or ";
					}
					sql += " category_id=" + cate[cate.length - 1] + ")";
				}
				PreparedStatement pstm = connection.prepareStatement(sql);
				pstm.setInt(1, from);
				pstm.setInt(2, to);
				rs = pstm.executeQuery();
			} else {
				sql = "SELECT COUNT (*) FROM Product where product_name like ? \n" + "and ( ";
				if (cate.length == 1) {
					sql += " category_id=" + cate[0] + ")";
				} else {
					for (int i = 0; i < cate.length - 1; i++) {
						sql += " category_id=" + cate[i] + " or ";
					}
					sql += " category_id=" + cate[cate.length - 1] + ")";
				}
				PreparedStatement pstm = connection.prepareStatement(sql);
				pstm.setString(1, "%" + name + "%");

				rs = pstm.executeQuery();
			}
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;
	}

	public Product getProductByProduct_id(int product_id) {
		Product product = new Product();
		String sql = "SELECT [product_id],[product_name],[quantity],[price],[description],[detail],[image_thumbnail],[status],[category_id],[shop_id] FROM [swp_shopping_online].[dbo].[Product] where product_id = "
				+ product_id;

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

	public ArrayList<Product> PaggingProductByCategory_id(int[] cate, int index, int size, String sortby) {
		ArrayList<Product> products = new ArrayList<>();
		String sql = "with x as (select RoW_Number() over (order by price " + sortby + " ) as r,* from Product where(";
		if (cate.length == 1) {
			sql += "category_id=" + cate[0] + ")) select * from x where r between ?*" + size + "- (" + size
					+ "-1) and ?*" + size + "";
		} else {
			for (int i = 0; i < cate.length - 1; i++) {
				sql += "category_id=" + cate[i] + " or ";
			}
			sql += "category_id=" + cate[cate.length - 1] + ")) select * from x where r between " + index + "*" + size
					+ "- (" + size + "-1) and " + index + "*" + size + "";
		}
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
				product.setCategory_id(rs.getInt("category_id"));
				product.setShop_id(rs.getInt("shop_id"));
				products.add(product);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
		return products;
	}

	public int countProductByCategory(int[] cate) {
		try {
			String sql = "SELECT count ( * ) FROM [Product] where (";
			if (cate.length == 1) {
				sql += "category_id=" + cate[0] + ") ";
			} else {
				for (int i = 0; i < cate.length - 1; i++) {
					sql += "category_id=" + cate[i] + " or ";
				}
				sql += "category_id=" + cate[cate.length - 1] + ")";
			}
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;
	}

	public ArrayList<Product> getProductsByCategory_id(int category_id) {
		ArrayList<Product> products = null;
		String sql = "select * from Product where category_id = ?";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, category_id);
			ResultSet rs = pstm.executeQuery();
			if (rs != null) {
				products = new ArrayList<>();
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
					product.setCategory_id(rs.getInt("category_id"));
					product.setShop_id(rs.getInt("shop_id"));
					products.add(product);
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
		return products;
	}
	final String findbyname = "SELECT * FROM Product where product_name like ? order by price ";
	final String filter_Cate = "SELECT * FROM Product where product_name like ? and category_id=? order by price ";
	final String filter_name_Cate_price = "SELECT * FROM Product where product_name like ?  and category_id=?\n"
			+ "and price between ? and ? order by price ";
	final String filter_name_noCate_price = "SELECT * FROM Product where product_name like ?  and price between ? and ? order by price ";
	final String filter_Cate_price_Noname = "SELECT * FROM Product where category_id=?\n"
			+ "and price between ? and ? order by price ";
	final String filter_price_Noname_Nocate = "SELECT * FROM Product where price between ? and ? order by price ";

	public ArrayList<Product> filter_Product(String name, int[] cate, int page, String orderby) throws SQLException {

		ArrayList<Product> products = new ArrayList<>();
		String sql = "";
		ResultSet rs = null;
		if (cate != null) {
			sql = "SELECT * FROM Product where product_name like ? and (";
			if (cate.length == 1) {
				sql += "category_id=" + cate[0] + ") order by price " + orderby;
			} else {
				for (int i = 0; i < cate.length - 1; i++) {
					sql += "category_id=" + cate[i] + " or ";
				}
				sql += "category_id=" + cate[cate.length - 1] + ") order by price " + orderby;
			}
			sql += " \noffset ? rows\n"
					+ "FETCH NEXT 6 ROWS ONLY";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, "%" + name + "%");
			pstm.setInt(2, (page - 1) * 6);
			rs = pstm.executeQuery();
		} else {
			sql = findbyname;
			sql += " \noffset ? rows\n"
					+ "FETCH NEXT 6 ROWS ONLY";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, "%" + name + "%");
			pstm.setInt(2, (page - 1) * 6);
			rs = pstm.executeQuery();
		}
		while (rs.next()) {
			Product product = new Product();
			product.setProduct_id(rs.getInt("product_id"));
			product.setProduct_name(rs.getString("product_name"));
			product.setQuantity(rs.getInt("quantity"));
			product.setPrice(rs.getDouble("price"));
			product.setDescription(rs.getString("description"));
			product.setStatus(rs.getInt("status"));
			product.setCategory_id(rs.getInt("category_id"));
			product.setShop_id(rs.getInt("shop_id"));
			products.add(product);
		}
		return products;
	}

	public ArrayList<Product> filter_ProductbyPrice(String name, int[] cate, int from, int to, int page, String orderby) throws SQLException {
		ArrayList<Product> products = new ArrayList<>();
		String sql = "";

		ResultSet rs = null;
		if (name.equals("") && cate == null) {
			sql = filter_price_Noname_Nocate + orderby;
			sql += " \noffset ? rows\n"
					+ "FETCH NEXT 6 ROWS ONLY";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, from);
			pstm.setInt(2, to);
			pstm.setInt(3, (page - 1) * 6);
			rs = pstm.executeQuery();
		} else if (cate == null) {
			sql = filter_name_noCate_price + orderby;
			sql += " \noffset ? rows\n"
					+ "FETCH NEXT 6 ROWS ONLY";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, "%" + name + "%");
			pstm.setInt(2, from);
			pstm.setInt(3, to);
			pstm.setInt(4, (page - 1) * 6);
			rs = pstm.executeQuery();
		} else if (name.equals("")) {
			sql = "SELECT * FROM Product where price between ? and ? and (";
			if (cate.length == 1) {
				sql += "category_id=" + cate[0] + ") order by price";
			} else {
				for (int i = 0; i < cate.length - 1; i++) {
					sql += "category_id=" + cate[i] + " or ";
				}
				sql += "category_id=" + cate[cate.length - 1] + ") order by price " + orderby;
			}
			sql += " \noffset ? rows\n"
					+ "FETCH NEXT 6 ROWS ONLY";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, from);
			pstm.setInt(2, to);
			pstm.setInt(3, (page - 1) * 6);
			rs = pstm.executeQuery();
		} else {
			sql = "SELECT * FROM Product where product_name like ? and price between ? and ? and (";
			if (cate.length == 1) {
				sql += " category_id=" + cate[0] + ") order by price";
			} else {
				for (int i = 0; i < cate.length - 1; i++) {
					sql += " category_id=" + cate[i] + " or ";
				}
				sql += " category_id=" + cate[cate.length - 1] + ") order by price " + orderby;
			}
			sql += " \noffset ? rows\n"
					+ "FETCH NEXT 6 ROWS ONLY";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, "%" + name + "%");
			pstm.setInt(2, from);
			pstm.setInt(3, to);
			pstm.setInt(4, (page - 1) * 6);
			rs = pstm.executeQuery();
		}

		while (rs.next()) {
			Product product = new Product();
			product.setProduct_id(rs.getInt("product_id"));
			product.setProduct_name(rs.getString("product_name"));
			product.setQuantity(rs.getInt("quantity"));
			product.setPrice(rs.getDouble("price"));
			product.setDescription(rs.getString("description"));
			product.setStatus(rs.getInt("status"));
			product.setCategory_id(rs.getInt("category_id"));
			product.setShop_id(rs.getInt("shop_id"));
			products.add(product);
		}
		return products;
	}

	public ArrayList<Product> getProductsByCategory_id(int[] cate, int page, String orderby) {
		ArrayList<Product> products = new ArrayList<>();
		String sql = "SELECT [product_id],[product_name],[quantity],[price],[description],[detail],[image_thumbnail],[status],[category_id],[shop_id] FROM [swp_shopping_online].[dbo].[Product] where (";
		if (cate.length == 1) {
			sql += "category_id=" + cate[0] + " order by price " + orderby;
		} else {
			for (int i = 0; i < cate.length - 1; i++) {
				sql += "category_id=" + cate[i] + " or ";
			}
			sql += "category_id=" + cate[cate.length - 1] + ") order by price " + orderby;
		}
		sql += " \noffset ? rows\n"
				+ "FETCH NEXT 6 ROWS ONLY";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, (page - 1) * 6);
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
				product.setCategory_id(rs.getInt("category_id"));
				product.setShop_id(rs.getInt("shop_id"));
				products.add(product);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
		return products;
	}

	public double countProductByCategory(String name, int[] cate, int from, int to) {
		try {
			String sql = "SELECT count ( * ) FROM [Product] where (price between ? and ?) and product_name like ? and (";
			if (cate != null) {
				if (cate.length == 1) {
					sql += "category_id=" + cate[0] + ") ";
				} else {
					for (int i = 0; i < cate.length - 1; i++) {
						sql += "category_id=" + cate[i] + " or ";
					}
					sql += "category_id=" + cate[cate.length - 1] + ")";
				}
			} else {
				sql = "SELECT count ( * ) FROM [Product] where (price between ? and ?) and product_name like ?";
			}
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, from);
			pstm.setInt(2, to);
			pstm.setString(3, "%" + name + "%");
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;
	}

	public double countAllProduct() {
		try {
			String sql = "SELECT count ( * ) FROM [Product]";

			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;
	}

	public double countProductbyname(String name) {
		try {
			String sql = "SELECT count ( * ) FROM [Product] where product_name like ? ";

			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, "%" + name + "%");
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;
	}

	public String getAllProductname() {
		String s = "";
		try {
			String sql = "select product_name from Product for json auto";
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
		}
		return s;
	}

	public ArrayList<Product> getAllProductsPaging(int page, String orderby) {
		ArrayList<Product> products = new ArrayList<>();
		try {
			String sql = "SELECT * FROM Product order by price " + orderby;
			sql += " \noffset ? rows\n"
					+ "FETCH NEXT 6 ROWS ONLY";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, (page - 1) * 6);
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

	public static void main(String[] args) throws SQLException {
		DAOProduct daoProduct = new DAOProduct();
//		 ArrayList<Product> products = daoProduct.getAllProducts();
//		  ArrayList<Product> products = daoProduct.getProductsByCategory_id(19);
		ArrayList<Product> products = daoProduct.getAllProducts();
//		  Product p = daoProduct.getProductByProduct_id(5);
//		  System.out.println(p.toString());

		for (Product product : products) {
			System.out.println(product.toString());
		}
	}
}
