package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Adduser {

	public static Boolean adduser(String user, String studentid, String name, String password) {
		Connection con = null;
		PreparedStatement preSql = null;
		String sqlStr = null;

		try {
			con = ConnectDatabase.connectDB();
			if (con == null) {
				System.out.println("Failed to establish database connection.");
				return false;
			}

			if (userlist()) {
				sqlStr = "INSERT INTO usertable (user, studentid, name, password, type) VALUES (?, ?, ?, ?, 0)";
			} else {
				sqlStr = "INSERT INTO usertable (user, studentid, name, password, type) VALUES (?, ?, ?, ?, 1)";
			}

			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, user);
			preSql.setString(2, studentid);
			preSql.setString(3, name);
			preSql.setString(4, password);

			int ok = preSql.executeUpdate();
			System.out.println("User added successfully.");
			return true;
		} catch (SQLException e) {
			System.out.println("Error during database operation.");
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "用户名已存在", "警告", JOptionPane.WARNING_MESSAGE);
			return false;
		} finally {
			closeResources(con, preSql, null);
		}
	}

	// 判断用户是否存在
	public static boolean userlist() {
		Connection con = null;
		PreparedStatement preSql = null;
		ResultSet rs = null;

		String sqlStr = "SELECT * FROM usertable";

		try {
			con = ConnectDatabase.connectDB();
			if (con == null) {
				System.out.println("Failed to establish database connection.");
				return false;
			}

			preSql = con.prepareStatement(sqlStr);
			rs = preSql.executeQuery();

			if (rs.next()) {
				return true; // 用户存在
			} else {
				return false; // 用户不存在
			}
		} catch (SQLException e) {
			System.out.println("Error during database operation.");
			e.printStackTrace();
			return false;
		} finally {
			closeResources(con, preSql, rs);
		}
	}

	// 关闭资源的方法
	private static void closeResources(Connection con, PreparedStatement preSql, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (preSql != null) {
				preSql.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("Error closing resources.");
			e.printStackTrace();
		}
	}
}
//测试修改
