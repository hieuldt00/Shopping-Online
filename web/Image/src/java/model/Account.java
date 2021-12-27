/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dal.DAOAccount;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ToneVn
 */
public class Account {
	private int account_id;
	private String account_username;
	private String account_password;
	private String account_name;
	private String email;
	private String account_phone;
	private boolean gender;
	private Date dob;
	private String account_address;
	private String account_image;
	private int status;
	ArrayList<Group> groups = new ArrayList<>();
	
	public Account() {
		status = 1;
	}
	public Account(int account_id, String account_username, String account_password, String account_name, String email, String account_phone, boolean gender, Date dob, String account_address, String account_image, int status, ArrayList<Group> groups) {
		this.account_id = account_id;
		this.account_username = account_username;
		this.account_password = account_password;
		this.account_name = account_name;
		this.email = email;
		this.account_phone = account_phone;
		this.gender = gender;
		this.dob = dob;
		this.account_address = account_address;
		this.account_image = account_image;
		this.status = status;
		this.groups = groups;
	}

	public Account(int account_id, String account_username, String account_password, String account_name, String email, String account_phone, boolean gender, Date dob, String account_address, String account_image, int status) {
		this.account_id = account_id;
		this.account_username = account_username;
		this.account_password = account_password;
		this.account_name = account_name;
		this.email = email;
		this.account_phone = account_phone;
		this.gender = gender;
		this.dob = dob;
		this.account_address = account_address;
		this.account_image = account_image;
		this.status = status;
	}
	
	
	
	
	public Account(String account_username, String account_password, String account_name, String email, String account_phone, boolean gender, Date dob, String account_address, String account_image, int status) {
		this.account_username = account_username;
		this.account_password = account_password;
		this.account_name = account_name;
		this.email = email;
		this.account_phone = account_phone;
		this.gender = gender;
		this.dob = dob;
		this.account_address = account_address;
		this.account_image = account_image;
		this.status = status;
	}
	public Account(String account_username, String account_password, String account_name,String email) {
		this.account_username = account_username;
		this.account_password = account_password;
		this.account_name = account_name;
                this.email= email;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public String getAccount_username() {
		return account_username;
	}

	public void setAccount_username(String account_username) {
		this.account_username = account_username;
	}

	public String getAccount_password() {
		return account_password;
	}

	public void setAccount_password(String account_password) {
		this.account_password = account_password;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccount_phone() {
		return account_phone;
	}

	public void setAccount_phone(String account_phone) {
		this.account_phone = account_phone;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAccount_address() {
		return account_address;
	}

	public void setAccount_address(String account_address) {
		this.account_address = account_address;
	}

	public String getAccount_image() {
		return account_image;
	}

	public void setAccount_image(String account_image) {
		this.account_image = account_image;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ArrayList<Group> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return account_id+" - "+account_username +" - " + account_password; //To change body of generated methods, choose Tools | Templates.
	}
	
	public static void main(String[] args) throws SQLException {
		Account account;
		DAOAccount daoAccount = new DAOAccount();
		account = daoAccount.checkAccountLogin("test", "testtest");
		System.out.println(account);
	}
}
