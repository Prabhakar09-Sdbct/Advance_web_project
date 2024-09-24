package com.pm.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class JDBCDataSourceRb {

	private static JDBCDataSourceRb jds = null;
	
	private ComboPooledDataSource cpds = null;
	
	private JDBCDataSourceRb() {
		try {
			cpds = new ComboPooledDataSource();
			cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
			cpds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
			cpds.setUser("root");
			cpds.setPassword("root");
			cpds.setInitialPoolSize(5);
			cpds.setAcquireIncrement(5);
			cpds.setMaxPoolSize(30);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static JDBCDataSourceRb getInstance() {
		 if(jds == null) {
			 jds = new JDBCDataSourceRb();
		 }
		 return jds;
	}
	
	public static Connection getConnection() {
	    try {
			return getInstance().cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void closeConnection(Connection con, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(Connection conn, Statement stmt) {
		closeConnection(conn, stmt, null);
	}

	public static void closeConnection(Connection conn) {
		closeConnection(conn, null);
	}
	
}
