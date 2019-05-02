package hu.elte.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginTableCreator {

	public static void main(String[] args) throws Exception {
		String user = "abc";
		String password = "";
		String url = "jdbc:sqlite:sqlite_test.db";

		loadDbDriver("org.sqlite.JDBC");

		try (
			Connection conn = DriverManager.getConnection(url, user, password);
		) {
			createLoginTable(conn);
			addUsers(conn);
			getUsers(conn);
		}
			
	}
	
	private static void addUsers(Connection conn) throws SQLException {
		try (PreparedStatement prep = conn.prepareStatement("insert into logins VALUES (?, ?)")) {
			addUser(prep, "root", "root");
			addUser(prep, "toor", "toor");
			addUser(prep, "admin", "pass");
			addUser(prep, "mod", "dom");
			
			conn.setAutoCommit(false);
			prep.executeBatch();
			conn.setAutoCommit(true);
		}
	}
	
	private static void addUser(PreparedStatement prep, String username, String password) throws SQLException {
		prep.setString(1, username);
		prep.setString(2, password);
		prep.addBatch();
	}

	private static void createLoginTable(Connection conn) throws SQLException {
		try (
				Statement stat = conn.createStatement();
			) {
				stat.executeUpdate("drop table if exists logins;");
				stat.executeUpdate("create table logins (username varchar(80), password varchar(80));");
			}
	}
	
	private static void getUsers(Connection conn) throws SQLException {
		try (PreparedStatement prep = conn.prepareStatement("SELECT username, password FROM logins")) {
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				System.out.println(username + " " + password);
			}
		}
	}

	private static void loadDbDriver(String driverClassName) throws Exception {
		try {
			Class.forName(driverClassName);
		} catch (Exception e) {
			System.err.println("ERROR: failed to load JDBC driver.");
			throw e;
		}
	}
}
