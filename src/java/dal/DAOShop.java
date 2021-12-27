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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Image_Product;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.Shop;
import model.ShopAccount;

/**
 *
 * @author son
 */
public class DAOShop extends DBContext {

	public List<Shop> listShop() {
		List<Shop> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM Shop";
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Shop s = new Shop();
				s.setShop_id(rs.getInt("shop_id"));
				s.setShop_name(rs.getString("shop_name"));
				s.setStatus(rs.getInt("status"));
				list.add(s);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
		}
		return list;
	}

	public List<Product> ListProductShop(int shop_id) {
		List<Product> list = new ArrayList<>();
		String sql = "SELECT [product_id],[product_name],[quantity],[price],[description],[detail],[image_thumbnail],[status],[category_id],[shop_id] FROM [swp_shopping_online].[dbo].[Product] where shop_id = "
				+ shop_id;

		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setProduct_id(rs.getInt("product_id"));
				p.setProduct_name(rs.getString("product_name"));
				p.setQuantity(rs.getInt("quantity"));
				p.setPrice(rs.getDouble("price"));
				p.setDescription(rs.getString("description"));
				p.setDetail(rs.getString("detail"));
				p.setStatus(rs.getInt("status"));
				p.setImage_thumbnail(rs.getString("image_thumbnail"));
				p.setCategory_id(rs.getInt("category_id"));
				p.setShop_id(rs.getInt("shop_id"));
				list.add(p);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
		return list;
	}

	public ShopAccount getShopID(int account_id) {
		ShopAccount shopAccount = new ShopAccount();
		String sql = "SELECT  * FROM Shop_Account where account_id=" + account_id;
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				shopAccount.setShop_id(rs.getInt("shop_id"));
				shopAccount.setAccount_id(rs.getInt("account_id"));
				shopAccount.setStatus(rs.getInt("status"));
			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
		return shopAccount;
	}

	public List<Order> OrderListByAccountID(int shopID) {
		List<Order> list = new ArrayList<>();
		String sql = "select * from [Order] where shop_id=" + shopID;
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Order o = new Order();
				o.setAccount_id(rs.getInt("account_id"));
				o.setDateCreate(rs.getDate("dateCreate"));
				o.setOrder_address(rs.getString("order_address"));
				o.setOrder_id(rs.getInt("order_id"));
				o.setOrder_name(rs.getString("order_name"));
				o.setOrder_phone(rs.getString("order_phone"));
				o.setStatus((rs.getString("status") == null ? -1 : rs.getInt("status") == 1 ? 1 : 0));
				o.setAccount_id(rs.getInt("account_id"));
				o.setShop_id(rs.getInt("shop_id"));
				o.setTotal(rs.getDouble("total"));
				list.add(o);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
		return list;
	}

	public List<OrderDetail> OrderDetailByOrderID(int order_id) {
		List<OrderDetail> list = new ArrayList<>();
		String sql = "select * from [OrderDetail] where order_id=" + order_id;
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				OrderDetail o = new OrderDetail();
				o.setOrder_id(rs.getInt("order_id"));
				o.setPrice(rs.getDouble("price"));
				o.setProduct_id(rs.getInt("product_id"));
				o.setQuantity(rs.getInt("quantity"));
				o.setTotal(rs.getDouble("total"));
				list.add(o);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
		return list;
	}

	public Category GetCategoryByCategoryID(int category_id) {
		Category cate = new Category();
		String sql = "select * from Category where category_id=" + category_id;
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				cate.setCategory_id(rs.getInt("category_id"));
				cate.setCategory_image(rs.getString("category_image"));
				cate.setCategory_name(rs.getString("category_name"));
				cate.setStatus(rs.getInt("status"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
		return cate;
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

	public void DeleteOrder(int order_id) {
		String sql2 = "delete from [Order] where order_id=" + order_id;
		String sql1 = "delete from [OrderDetail] where order_id=" + order_id;
		try {
			PreparedStatement pstm = connection.prepareStatement(sql1);
			pstm.executeUpdate();
			PreparedStatement pstm2 = connection.prepareStatement(sql2);
			pstm2.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
	}

	public Order GetInFoByOrder_id(int order_id) {
		Order o = new Order();
		String sql = "select * from [Order] where order_id=" + order_id;
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				o.setAccount_id(rs.getInt("account_id"));
				o.setDateCreate(rs.getDate("dateCreate"));
				o.setOrder_address(rs.getString("order_address"));
				o.setOrder_id(rs.getInt("order_id"));
				o.setOrder_name(rs.getString("order_name"));
				o.setOrder_phone(rs.getString("order_phone"));
				o.setStatus((rs.getString("status") == null ? -1 : rs.getInt("status") == 1 ? 1 : 0));
				o.setAccount_id(rs.getInt("account_id"));
				o.setShop_id(rs.getInt("shop_id"));
				o.setTotal(rs.getDouble("total"));

			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
		return o;
	}

	public void AddNewProduct(String product_name, int quantity, double price, String description, String detail,
			int status, int category_id, int shop_id, String thumbnail) {
		String sql = "insert into Product(product_name,quantity,price,description,detail,status,category_id,shop_id,image_thumbnail) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, product_name);
			pstm.setInt(2, quantity);
			pstm.setDouble(3, price);
			pstm.setString(4, description);
			pstm.setString(5, detail);
			pstm.setInt(6, status);
			pstm.setInt(7, category_id);
			pstm.setInt(8, shop_id);
			pstm.setString(9, thumbnail);
			pstm.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
	}

	public void DeleteProduct(int product_id) {
		String sql = "delete from Product where product_id=" + product_id;
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}

	}

	public void EditProduct(String product_name, int quantity, double price, String description, int status, String image_thumbnail, int category_id, int shop_id, String detail, int product_id) {
		String sql = "update  Product set [product_name]=?,[quantity]=?,[price]=?,[description]=?,[status]=?,[image_thumbnail]=?,[category_id]=?,[shop_id]=?,[detail]=? where product_id=" + product_id;
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, product_name);
			pstm.setInt(2, quantity);
			pstm.setDouble(3, price);
			pstm.setString(4, description);
			pstm.setInt(5, status);
			pstm.setString(6, image_thumbnail);
			pstm.setInt(7, category_id);
			pstm.setInt(8, shop_id);
			pstm.setString(9, detail);
			pstm.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
	}

	public void AddNewImageProduct(String image, int product_id) {
		String sql = "insert into Image_Product(url,product_id,status) values(?,?,?)";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, image);
			pstm.setInt(2, product_id);
			pstm.setInt(3, 1);
			pstm.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
	}

	public Product getLastProduct() {
		Product products = new Product();
		String sql = "select top 1 * from Product order by product_id desc";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				products.setProduct_id(rs.getInt("product_id"));
				products.setProduct_name(rs.getString("product_name"));
				products.setQuantity(rs.getInt("quantity"));
				products.setPrice(rs.getDouble("price"));
				products.setDescription(rs.getString("description"));
				products.setStatus(rs.getInt("status"));
				products.setImage_thumbnail(rs.getString("image_thumbnail"));
				products.setCategory_id(rs.getInt("category_id"));
				products.setShop_id(rs.getInt("shop_id"));
				products.setDetail(rs.getString("detail"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
		return products;
	}

	public List<Image_Product> ImageByProduct_id(int product_id) {
		List<Image_Product> list = new ArrayList();
		String sql = "select * from Image_Product where product_id=" + product_id;
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Image_Product i = new Image_Product();
				i.setProduct_id(rs.getInt("product_id"));
				i.setUrl(rs.getString("url"));
				i.setStatus(rs.getInt("status"));
				list.add(i);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
		return list;
	}

	public List<Order> OrderCancell(int shop_id) {
		List<Order> list = new ArrayList<>();
		String sql = "select * from [Order] where [status] is NULL and shop_id=" + shop_id;
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Order o = new Order();
				o.setAccount_id(rs.getInt("account_id"));
				o.setDateCreate(rs.getDate("dateCreate"));
				o.setOrder_address(rs.getString("order_address"));
				o.setOrder_id(rs.getInt("order_id"));
				o.setOrder_name(rs.getString("order_name"));
				o.setOrder_phone(rs.getString("order_phone"));
				o.setStatus((rs.getString("status") == null ? -1 : rs.getInt("status") == 1 ? 1 : 0));
				o.setAccount_id(rs.getInt("account_id"));
				o.setShop_id(rs.getInt("shop_id"));
				o.setTotal(rs.getDouble("total"));
				list.add(o);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
		return list;
	}

	public Shop getShopByShopID(int shop_id) {
		Shop shop = new Shop();
		String sql = "SELECT  * FROM Shop where shop_id=?";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, shop_id);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				shop.setShop_id(rs.getInt("shop_id"));
				shop.setShop_name(rs.getString("shop_name"));
				shop.setStatus(rs.getString("status") == null ? -1 : rs.getInt("status") == 1 ? 1 : 0);
			}

		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
		return shop;
	}

	public static void main(String[] args) {
		DAOShop dao = new DAOShop();
		List<Product> list = dao.ListProductShop(5);
		for (Product p : list) {
			System.out.println(p);
		}

	}

	public boolean updateStatusShop(int id, boolean ban) {
		String sql;
		if (ban) {
			sql = "update Shop set status = 0 where shop_id=?"
					+ "\nupdate Shop_Account set status=0 where shop_id=?";
		} else {
			sql = "update Shop set status = 1 where shop_id=?"
					+ "\nupdate Shop_Account set status=1 where shop_id=?";
		}
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.setInt(2, id);
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Shop GetShopById(int shop_id) {
		Shop shop = new Shop();
		try {
			String sql = "SELECT * FROM Shop where shop_id=" + shop_id;
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {

				shop.setShop_id(rs.getInt("shop_id"));
				shop.setShop_name(rs.getString("shop_name"));
				shop.setShop_image(rs.getString("shop_image"));
				shop.setStatus(rs.getInt("status"));

			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
		}
		return shop;
	}

	public void editShop(int shop_id, String shop_name, String shop_image) {
		try {
			String sql = "update Shop set shop_name=?,shop_image=? where shop_id=" + shop_id;
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, shop_name);
			pstm.setString(2, shop_image);
			pstm.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
	}

	public void EditImage_Product(int product_id, String imageEdit, String imageBefore) {
		try {
			String sql = "update Image_Product set url=? where product_id=? and url=?";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, imageEdit);
			pstm.setInt(2, product_id);
			pstm.setString(3, imageBefore);
			pstm.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
	}

}
