package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBCon {
	public Connection con;
	public PreparedStatement pstmt;
	public ResultSet rs;
	
	public DBCon() {
		String url = "jdbc:mysql://localhost:3306/bookdb";
		String id = "root";
		String pass = "1234";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, id, pass);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null)pstmt.close();
			if(con != null)con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
