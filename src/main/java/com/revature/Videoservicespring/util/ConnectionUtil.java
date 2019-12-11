package com.revature.Videoservicespring.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class ConnectionUtil {

	private static final String driverClassName = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/video";
	private static final String username = "root";
	private static final String password = "root";

	public static Connection getConnection() {

		Connection con = null;

		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, username, password);
			System.out.println(con);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to load the driver class");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to get DB Connection");
		}

		return con;
	}

	public static void close(Connection con, PreparedStatement pst) {

		try {
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			System.out.println("Unable to close connection");
		}

	}

	public static void closeRs(ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("Unable to close ResultSet");
			}
	}
}
