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
import model.Account;
import model.Category;
import model.Product;
import model.Role;
import model.Shop;

/**
 *
 * @author duong
 */
public class DAOAdmin extends DBContext {

	public ArrayList<Shop> findshops(String name) {
		ArrayList<Shop> shops = new ArrayList<>();
		try {
			String sql = "SELECT * FROM [dbo].[Shop] where shop_name like ? or shop_id like ?";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, "%" + name + "%");
			pstm.setString(2, "%" + name + "%");
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Shop s = new Shop();
				s.setShop_id(rs.getInt(1));
				s.setShop_name(rs.getString(2));
				s.setStatus(rs.getInt(4));
				shops.add(s);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
		return shops;
	}

	public ArrayList<Shop> gettop10Shops(String dateFrom, String dateTo) {
		ArrayList<Shop> shops = new ArrayList<>();
		try {
			String sql = "with t as (SELECT o.shop_id,s.shop_name,sum([total]) totalsell\n"
					+ "  FROM [dbo].[Order] o join Shop s on o.shop_id=s.shop_id\n"
					+ "  where dateCreate between ? and ?\n"
					+ "  group by o.shop_id,s.shop_name\n"
					+ "  )\n"
					+ "  select top 10 * from t\n"
					+ "  order by totalsell desc";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, dateFrom);
			pstm.setString(2, dateTo);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Shop s = new Shop();
				s.setShop_id(rs.getInt(1));
				s.setShop_name(rs.getString(2));
				s.setStatus(rs.getInt(3));
				shops.add(s);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
		return shops;
	}

	public ArrayList<Product> getBestseller() {
		ArrayList<Product> shops = new ArrayList<>();
		try {
			String sql = "with t as (SELECT p.product_id,p.product_name,SUM(od.price*od.quantity) total\n"
					+ "  FROM [dbo].[OrderDetail] od join Product p on p.product_id = od.product_id join [dbo].[Order] o on o.order_id=od.order_id\n"
					+ "  where dateCreate between GETDATE()-30 and GETDATE()\n"
					+ "  group by p.product_id,p.product_name\n"
					+ "  )\n"
					+ "  select top(10) t.product_id,t.product_name , t.total from t\n"
					+ "  order by total desc";
			PreparedStatement pstm = connection.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Product s = new Product();
				s.setProduct_id(rs.getInt(1));
				s.setProduct_name(rs.getString(2));
				s.setPrice(rs.getInt(3));
				shops.add(s);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
		return shops;
	}

	public String getDataRevenue(String year) {
		String s = "";
		try {
			String sql = "SELECT Month(dateCreate) [monthInt], DATENAME(month,dateCreate) [month], \n"
					+ "    SUM(total) total\n"
					+ "FROM [dbo].[Order]\n"
					+ "WHERE dateCreate IS NOT NULL\n"
					+ "    AND YEAR(dateCreate) = ?\n"
					+ "GROUP BY DATENAME(month,dateCreate),Month(dateCreate)\n"
					+ "ORDER BY [monthInt]\n"
					+ "for json auto";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, year);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				s = rs.getString(1);
				break;
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
		return s;
	}

	public String getDataCategories(String monthFrom, String monthTo) {
		String s = "";
		try {
			String sql = "with ta as (SELECT c.category_id\n"
					+ "      ,[category_name]\n"
					+ "      ,c.status\n"
					+ "      ,[category_image], sum(total) total\n"
					+ "FROM Category c left join [dbo].[Order] o  on o.category_id=c.category_id\n"
					+ "where dateCreate between ? and ?\n"
					+ "group by c.category_id\n"
					+ "      ,[category_name]\n"
					+ "      ,c.status\n"
					+ "      ,[category_image]\n"
					+ "union\n"
					+ "(select category_id\n"
					+ "      ,[category_name]\n"
					+ "      ,status\n"
					+ "      ,[category_image],total=0  from Category))\n"
					+ "select ta.category_name,sum(ta.total) total from ta\n"
					+ "group by ta.category_name\n"
					+ "  for json auto";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, monthFrom);
			pstm.setString(2, monthTo);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				s = rs.getString(1);
				break;
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
		return s;
	}

	public int[] getPageInfo() {
		int[] re = new int[3];
		try {
			String sql = "SELECT count(account_id) from Account where status=1";
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				re[0] = rs.getInt(1);
			}
			sql = "SELECT count(shop_id) from Shop where status=1";
			pstm = connection.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				re[1] = rs.getInt(1);
			}
			sql = "SELECT count(*) from [dbo].[Order] where dateCreate between GETDATE()-7 and GETDATE()";
			pstm = connection.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				re[2] = rs.getInt(1);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
		return re;
	}

	public ArrayList<Account> findAccount(String name, String gender, String status, String[] role, int currentUser) {
		ArrayList<Account> accounts = new ArrayList<>();
		String sql = "SELECT distinct [account_id]\n"
				+ "      ,[account_username]\n"
				+ "      ,[account_password]\n"
				+ "      ,[account_name]\n"
				+ "      ,[email]\n"
				+ "      ,[account_phone]\n"
				+ "      ,[gender]\n"
				+ "      ,[dob]\n"
				+ "      ,[account_address]\n"
				+ "      ,[account_image]\n"
				+ "      ,[status] FROM Account a full outer  join Account_Group ag on a.account_id=ag.id where  (account_name like ? or email like ? or account_phone like ?) and gender like ? and status like ? and group_id not in (1) and account_id not in (?) ";
		ResultSet rs = null;
		try {
			if (role == null) {
				PreparedStatement pstm = connection.prepareStatement(sql);
				for (int i = 1; i <= 3; i++) {
					pstm.setString(i, "%" + name + "%");
				}
				pstm.setString(4, "%" + gender + "%");
				pstm.setString(5, "%" + status + "%");
				pstm.setInt(6, currentUser);
				rs = pstm.executeQuery();
			} else {
				sql += " and (";
				if (role.length == 1) {
					sql += " group_id = " + role[0] + ")";
				} else {
					for (int i = 0; i < role.length - 1; i++) {
						sql += " group_id = " + role[i] + " or";
					}
					sql += " group_id = " + role[role.length - 1] + ")";
				}

				PreparedStatement pstm = connection.prepareStatement(sql);
				for (int i = 1; i <= 3; i++) {
					pstm.setString(i, "%" + name + "%");
				}
				pstm.setString(4, "%" + gender + "%");
				pstm.setString(5, "%" + status + "%");
				pstm.setInt(6, currentUser);
				rs = pstm.executeQuery();
			}

			while (rs.next()) {
				Account a = new Account();
				a.setAccount_id(rs.getInt("account_id"));
				a.setAccount_username(rs.getString("account_username"));
				a.setAccount_password(rs.getString("account_password"));
				a.setAccount_name(rs.getString("account_name"));
				a.setEmail(rs.getString("email"));
				a.setAccount_phone(rs.getString("account_phone"));
				a.setGender(rs.getBoolean("gender"));
				a.setDob(rs.getDate("dob"));
				a.setAccount_address(rs.getString("account_address"));
				a.setAccount_image(rs.getString("account_image"));
				a.setStatus(rs.getInt("status"));
				accounts.add(a);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
		}
		return accounts;
	}

	public ArrayList<Role> getAllRole() {
		ArrayList<Role> roles = new ArrayList<>();
		String sql = "SELECT * FROM [swp_shopping_online].[dbo].[Group] where group_id not in(1)";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {

				roles.add(new Role(rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
		}
		return roles;
	}

	public ArrayList<Integer> getRolebyID(int id) {
		ArrayList<Integer> roles = new ArrayList<>();
		String sql = "SELECT group_id FROM Account_Group  where id=?";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				roles.add(rs.getInt(1));
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
		}
		return roles;
	}

	public boolean updateUserRole(int id, String[] roles) {
		String sql = "DELETE FROM [dbo].[Account_Group]WHERE id=?\n"
				+ "INSERT INTO [dbo].[Account_Group]([id],[group_id])VALUES";
		if (roles.length == 1) {
			sql += "(" + id + "," + roles[0] + ")";
		} else {
			for (int i = 0; i < roles.length - 1; i++) {
				sql += "(" + id + "," + roles[i] + "),";
			}
			sql += "(" + id + "," + roles[roles.length - 1] + ")";
		}
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean checkUsernameExist(String username) {
		Account account = null;
		String sql = "select * from Account where account_username = ?";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, username);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	public boolean checkCategoryExist(String category) {
		Account account = null;
		String sql = "SELECT [category_id]  FROM [dbo].[Category] where category_name=?";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, category);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

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

	public String insertCategory(String name, String img) {
		String s = "";
		try {
			String sql = "INSERT INTO [dbo].[Category]\n"
					+ "           ([category_name]\n"
					+ "           ,[status]\n"
					+ "           ,[category_image])\n"
					+ "     VALUES(?,1,?)";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, img);
			pstm.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
		return s;
	}

	public String controlCategory(String name, String status) {
		String s = "";
		String sql;
		try {
			PreparedStatement pstm;
			if (name != null) {
				sql = "UPDATE [dbo].[Category] SET [status] = ? WHERE category_name=?";
				pstm = connection.prepareStatement(sql);
				pstm.setString(1, status);
				pstm.setString(2, name);
			} else {
				sql = "UPDATE [dbo].[Category] SET [status] = ?";
				pstm = connection.prepareStatement(sql);
				pstm.setString(1, status);
			}

			pstm.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
		return s;
	}

	public String getAllEmail() {
		ArrayList<Category> categories = new ArrayList<>();
		try {
			String sql = "SELECT  account_name,email \n"
					+ "  FROM [swp_shopping_online].[dbo].[Account]\n"
					+ "  where email is not null\n"
					+ "  for json auto";
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

}
