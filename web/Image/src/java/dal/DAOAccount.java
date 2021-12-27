/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author ToneVn
 */
public class DAOAccount extends DBContext {

//	create table [Account](
//	account_id int primary key identity(1,1),
//	account_username varchar(30) not null unique,
//	account_password varchar(30) not null check(len(account_password)>=8),
//	account_name varchar(50) not null,
//	email varchar(100),
//	account_phone varchar(15),
//	gender bit,
//	dob date,
//	account_address varchar(200),
//	account_image varchar(100),
//	shop_id int default 0,
//	[status] bit default 1
//)
	public ArrayList<Account> getAllAccount() {
		ArrayList<Account> accounts = new ArrayList<>();

		try {
			String sql = "SELECT * FROM Account";
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
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

	public Account checkAccountLogin(String account_username, String account_password) throws SQLException{
		Account account = null;
		String sql = "select * from Account where account_username = ? and account_password = ?";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, account_username);
			pstm.setString(2, account_password);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				account = new Account();
				account.setAccount_id(rs.getInt("account_id"));
				account.setAccount_username(rs.getString("account_username"));
				account.setAccount_password(rs.getString("account_password"));
				account.setAccount_name(rs.getString("account_name"));
				account.setEmail(rs.getString("email"));
				account.setAccount_phone(rs.getString("account_phone"));
				account.setGender(rs.getBoolean("gender"));
				account.setDob(rs.getDate("dob"));
				account.setAccount_address(rs.getString("account_address"));
				account.setAccount_image(rs.getString("account_image"));
				account.setStatus(rs.getInt("status"));
				return account;
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
		}finally{
			connection.close();
		}
		return null;

	}

	public int addAccount(Account account) {
		int n = 0;
//		String sql = "insert into Account(cname,cphone,cAddress,username,password,status) values(?,?,?,?,?,?)";
//		String sql = "insert into Account(account_username,account_password,account_name,email,account_phone,gender,dob,account_address,account_image) values(?,?,?,?,?,?,?,?,?)";
		String sql = "insert into Account(account_username,account_password,account_name,email) values(?,?,?,?)";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, account.getAccount_username());
			pstm.setString(2, account.getAccount_password());
			pstm.setString(3, account.getAccount_name());
			pstm.setString(4, account.getEmail());
//			pstm.setString(5, account.getAccount_phone());
//			pstm.setBoolean(6, account.isGender());
//			pstm.setDate(7, new java.sql.Date(account.getDob().getTime()));
//			pstm.setString(8, account.getAccount_address());
//			pstm.setString(9, account.getAccount_image());
			n = pstm.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
		}
		return n;
	}

	public int editAccountProfile(String email, String phone, String address, String gender, String dob, String id) {
		int n = 0;

		String sql = "update Account set email=?,account_phone=?,account_address=?,gender=?,dob=? where account_id =?";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);

			pstm.setString(1, email);
			pstm.setString(2, phone);
			pstm.setString(3, address);
			pstm.setString(4, gender);
			pstm.setDate(5, Date.valueOf(dob));

			pstm.setString(6, id);
			n = pstm.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
		}
		return n;
	}

	public Account getAccountByAccountId(int id) {
		Account account = null;
		String sql = "select * from Account where account_id = " + id;
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				account = new Account();
				account.setAccount_id(rs.getInt("account_id"));
				account.setAccount_username(rs.getString("account_username"));
				account.setAccount_password(rs.getString("account_password"));
				account.setAccount_name(rs.getString("account_name"));
				account.setEmail(rs.getString("email"));
				account.setAccount_phone(rs.getString("account_phone"));
				account.setGender(rs.getBoolean("gender"));
				account.setDob(rs.getDate("dob"));
				account.setAccount_address(rs.getString("account_address"));
				account.setAccount_image(rs.getString("account_image"));
//				account.setShop_id(rs.getInt("shop_id"));
				account.setStatus(rs.getInt("status"));
				return account;
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

//	public static void main(String[] args) {
//		DAOAccount d = new DAOAccount();
//		Account a = d.checkAccountLogin("test", "testtest");
////		Account a = new Account("Customer", "11111111", "Customer Test","a@gmail.com");
////		int n = 0;
////		n = d.addAccount(a);
//		System.out.println(a);
//	}
}
