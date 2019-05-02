package hu.elte.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientAuthenticator {

	Connection connection;

	public static void main(String[] args) throws Exception {
		ClientAuthenticator clientAuthenticator = new ClientAuthenticator();

		listenForClients(clientAuthenticator);
	}

	private static void listenForClients(ClientAuthenticator clientAuthenticator) throws IOException, SQLException {
		ServerSocket ss = new ServerSocket(12345);
		ExecutorService executor = Executors.newCachedThreadPool();

		for (;;) {
			executor.submit(new ClientHandler(ss.accept(), clientAuthenticator));
		}
	}

	public ClientAuthenticator() throws Exception {
		String user = "abc";
		String password = "";
		String url = "jdbc:sqlite:sqlite_test.db";
		this.connection = DriverManager.getConnection(url, user, password);

		loadDbDriver("org.sqlite.JDBC");
		this.createTables(connection);
		this.addUsers(connection);
	}

	private void addUsers(Connection conn) throws SQLException {
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

	private void addUser(PreparedStatement prep, String username, String password) throws SQLException {
		prep.setString(1, username);
		prep.setString(2, password);
		prep.addBatch();
	}

	private void createTables(Connection conn) throws SQLException {
		try (Statement stat = conn.createStatement();) {
			stat.executeUpdate("drop table if exists logins;");
			stat.executeUpdate("create table logins (username varchar(80), password varchar(80));");
			stat.executeUpdate("drop table if exists basket;");
			stat.executeUpdate("create table basket (username varchar(80), item varchar(80), quantity int);");
			stat.executeUpdate("drop table if exists saved;");
			stat.executeUpdate("create table saved (username varchar(80), item varchar(80), quantity int);");
		}
	}

	private void getUsers(Connection conn) throws SQLException {
		try (PreparedStatement prep = conn.prepareStatement("SELECT username, password FROM logins")) {
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				System.out.println(username + " " + password);
			}
		}
	}

	private void loadDbDriver(String driverClassName) throws Exception {
		try {
			Class.forName(driverClassName);
		} catch (Exception e) {
			System.err.println("ERROR: failed to load JDBC driver.");
			throw e;
		}
	}

	public boolean isCorrectCredentials(String username, String password) throws SQLException {
		boolean isCorrect = false;
		try (PreparedStatement prep = connection
				.prepareStatement("SELECT count(*) FROM logins WHERE username = ? and password = ?")) {
			prep.setString(1, username);
			prep.setString(2, password);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				isCorrect = rs.getInt(1) == 1;
			}
		}

		return isCorrect;
	}

	public void addToBasket(String username, String item, int quantity) throws SQLException {
		try (PreparedStatement prep = connection.prepareStatement("INSERT INTO basket VALUES (?, ?, ?);")) {
			prep.setString(1, username);
			prep.setString(2, item);
			prep.setInt(3, quantity);

			prep.executeUpdate();
		}
	}

	public String listBasket(String username) throws SQLException {
		StringBuilder returnString = new StringBuilder();
		try (PreparedStatement prep = connection
				.prepareStatement("SELECT item, quantity FROM basket WHERE username = ?;")) {
			prep.setString(1, username);

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				String item = rs.getString(1);
				int quantity = rs.getInt(2);

				returnString.append(String.format("Item name: %s, quantity: %d", item, quantity));
			}
		}
		return returnString.toString();
	}

	public void saveList(String username) throws SQLException {
		try (PreparedStatement cartItems = connection
				.prepareStatement("SELECT item, quantity FROM basket WHERE username = ?;");
				PreparedStatement saveCartStatement = connection
						.prepareStatement("INSERT INTO saved VALUES(?, ?, ?);")) {
			cartItems.setString(1, username);
			ResultSet rs = cartItems.executeQuery();

			while (rs.next()) {
				saveCartStatement.setString(1, username);
				saveCartStatement.setString(2, rs.getString(1));
				saveCartStatement.setInt(3, rs.getInt(2));
				saveCartStatement.executeUpdate();
			}
			this.discardBasket(username);
		}
	}

	public int soldItemsCount() throws SQLException {
		try (PreparedStatement soldItemsCount = connection.prepareStatement("SELECT SUM(quantity) FROM saved;")) {
			ResultSet rs = soldItemsCount.executeQuery();

			return rs.getInt(1);
		}
	}

	public void discardBasket(String username) throws SQLException {
		try (PreparedStatement discardCart = connection.prepareStatement("DELETE FROM basket WHERE username = ?;")) {
			discardCart.setString(1, username);
			discardCart.executeUpdate();
		}
	}

	public void removeFromBasket(String username, String item, int quantity) throws SQLException {
		try (PreparedStatement updateBasket = connection
				.prepareStatement("UPDATE basket SET quantity = quantity - ? WHERE username = ? AND item = ?;")) {
			updateBasket.setInt(1, quantity);
			updateBasket.setString(2, username);
			updateBasket.setString(3, item);
			updateBasket.executeUpdate();
		}
	}

	public void deleteFromBasket(String username, String item) throws SQLException {
		try (PreparedStatement deleteItemFromBasket = connection
				.prepareStatement("DELETE FROM basket WHERE username = ? AND item = ?;")) {
			deleteItemFromBasket.setString(1, username);
			deleteItemFromBasket.setString(2, item);
			deleteItemFromBasket.executeUpdate();
		}
	}
}

class ClientHandler implements Runnable {

	Socket socket;
	ClientAuthenticator clientAuthenticator;
	Scanner input;
	PrintWriter output;
	private String username;
	private String password;

	public ClientHandler(Socket socket, ClientAuthenticator clientAuthenticator) throws IOException, SQLException {
		this.socket = socket;
		this.clientAuthenticator = clientAuthenticator;
		this.input = new Scanner(socket.getInputStream());
		this.output = new PrintWriter(socket.getOutputStream());
	}

	private void handleLogin() throws SQLException, IOException {
		username = input.nextLine();
		password = input.nextLine();

		output.println(this.clientAuthenticator.isCorrectCredentials(username, password) ? "You're logged in."
				: "Invalid credentials");
		output.flush();
	}

	@Override
	public void run() {
		try {
			handleLogin();
			handleCommands();
			socket.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	private void handleCommands() throws NumberFormatException, SQLException {
		while (input.hasNextLine()) {
			String command = input.nextLine();

			if (command.startsWith("kosarba")) {
				String[] parameters = command.split(" ");
				if (parameters.length == 3) {
					clientAuthenticator.addToBasket(username, parameters[1], Integer.parseInt(parameters[2]));
				} else {
					output.println("Incorrect number of parameters!");
					output.flush();
				}
			} else if (command.equals("list")) {
				output.println(clientAuthenticator.listBasket(username));
				output.flush();
			} else if (command.equals("veglegesit")) {
				clientAuthenticator.saveList(username);
				output.println(clientAuthenticator.soldItemsCount());
				output.flush();
			} else if (command.equals("eldob")) {
				clientAuthenticator.discardBasket(username);
			} else if (command.startsWith("kosarbol")) {
				String[] parameters = command.split(" ");
				if (parameters.length == 3) {
					clientAuthenticator.removeFromBasket(username, parameters[1], Integer.parseInt(parameters[2]));
				} else {
					output.println("Incorrect number of parameters!");
					output.flush();
				}
			} else if(command.startsWith("torol")) {
				String[] parameters = command.split(" ");
				if(parameters.length == 2) {
					clientAuthenticator.deleteFromBasket(username, parameters[1]);
				} else {
					output.println("Incorrect number of parameters!");
					output.flush();
				}
			} else {
				output.println("Incorrect command");
				output.flush();
			}
		}
	}
}
