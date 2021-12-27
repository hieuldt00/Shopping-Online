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
import model.Group;

/**
 *
 * @author ToneVn
 */
public class DAOGroup extends DBContext {

	public ArrayList<Group> getAllGroup() {
		ArrayList<Group> groups = new ArrayList<>();
		String sql = "select * from [Group]";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Group group = new Group();
				group.setGroup_id(rs.getInt("group_id"));
				group.setGroup_name(rs.getString("group_name"));
				groups.add(group);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAOGroup.class.getName()).log(Level.SEVERE, null, ex);
		}
		return groups;
	}

	public ArrayList<Group> getGroupByAccountUsername(String username) throws SQLException {
		ArrayList<Group> groups = new ArrayList<>();
		String sql = "select g.group_id, g.group_name from Account a inner join Account_Group ag on a.account_id = ag.id\n"
				+ "inner join [Group] g on ag.group_id = g.group_id where a.account_name like '"+username+"'";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Group group = new Group();
				group.setGroup_id(rs.getInt("group_id"));
				group.setGroup_name(rs.getString("group_name"));
				groups.add(group);
			}
			return groups;
		} catch (SQLException ex) {
			Logger.getLogger(DAOGroup.class.getName()).log(Level.SEVERE, null, ex);
		}finally{
			connection.close();
		}
		return null;
	}

	public static void main(String[] args) throws SQLException {
		DAOGroup daoGroup = new DAOGroup();
		ArrayList<Group> groups = daoGroup.getGroupByAccountUsername("admin");
		for (Group group : groups) {
			System.out.println(group);
		}
	}
}
