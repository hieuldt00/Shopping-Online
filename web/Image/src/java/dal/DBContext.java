/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ToneVn
 */
public class DBContext {
	Connection connection;

	public DBContext() {
		try {
			String url = "jdbc:sqlserver://localhost:1433;databaseName=swp_shopping_online";
			String user = "sa";
			String password = "123456";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception ex) {
			Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
		}		
	}
}