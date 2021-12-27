/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author ToneVn
 */
public class Group {
	private int group_id;
	private String group_name;
	ArrayList<Account> accounts = new ArrayList<>();

	public Group() {
	}

	public Group(int group_id, String group_name) {
		this.group_id = group_id;
		this.group_name = group_name;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
//		return "Group{" + "group_id=" + group_id + ", group_name=" + group_name + ", accounts=" + accounts + '}';
		return "Group{" + "group_id=" + group_id + ", group_name=" + group_name+ '}';
	}
	
}
