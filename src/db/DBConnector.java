package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.HashMap;
import com.google.gson.*;

import model.Book;
import model.User;

public class DBConnector {

	Connection connection;

	public boolean connectToMySQL(String host, String database, String user, String pw) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connectCmd = "jdbc:mysql://" + host + "/" + database + "?user=" + user + "&password=" + pw;
			connection = DriverManager.getConnection(connectCmd);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception: " + e.getMessage());
		}

		return false;

	}

	public boolean writeBookIntoDB(String bookAsJSON) {
		try {
			System.out.println("Trying to write into DB");

			String sqlQuery = "INSERT INTO book VALUES (default, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, bookAsJSON);
			preparedStatement.executeUpdate();

			// ResultSet rs = statement.executeQuery();
			System.out.println("Wrote into DB");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception: " + e.getMessage());
		}

		return false;
	}

	public boolean writeUserIntoDB(String userAsJSON) {
		try {
			System.out.println("Trying to write into DB");

			String sqlQuery = "INSERT INTO User VALUES (default, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, userAsJSON);
			preparedStatement.executeUpdate();

			// ResultSet rs = statement.executeQuery();
			System.out.println("Wrote into DB");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception: " + e.getMessage());
		}

		return false;
	}

	public Map<Integer, Book> getAllBooks() {

		Map<Integer, Book> allListedBooks = new HashMap<Integer, Book>();

		try {
			// System.out.println("All Books");

			Statement statement = connection.createStatement();
			String sqlQuery = "select * from book";

			ResultSet rs = statement.executeQuery(sqlQuery);

			while (rs.next()) {
				String json = rs.getString("bookObj");
				Integer i = rs.getInt("idbook");
				// System.out.println("JSON: " + json);
				Gson gson = new GsonBuilder().create();
				Book b = gson.fromJson(json, Book.class);
				allListedBooks.put(i, b);
			}

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}

		return allListedBooks;
	}

	public Map<Integer, User> getAllUsers() {

		Map<Integer, User> allListedUsers = new HashMap<Integer, User>();

		try {
			// System.out.println("All Books");

			Statement statement = connection.createStatement();
			String sqlQuery = "select * from User";

			ResultSet rs = statement.executeQuery(sqlQuery);

			while (rs.next()) {
				String json = rs.getString("userObj");
				Integer i = rs.getInt("idUser");
				// System.out.println("JSON: " + json);
				Gson gson = new GsonBuilder().create();
				User u = gson.fromJson(json, User.class);
				allListedUsers.put(i, u);
			}

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}

		return allListedUsers;
	}

	public boolean closeDBConnection() {
		try {
			connection.close();

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception: " + e.getMessage());
		}

		return false;
	}

	public User getUser(String name) throws SQLException {
		User u = null;

		Statement statement = connection.createStatement();
		String sqlQuery = "select * from user";

		// Iterate through all users and get the user with the surname
		ResultSet rs = statement.executeQuery(sqlQuery);
		while (rs.next()) {
			String json = rs.getString("userObj");
			// System.out.println("JSON: " + json);
			Gson gson = new GsonBuilder().create();
			u = gson.fromJson(json, User.class);
			// System.out.println(u.getSurname());

			if (u.getName().equals(name)) {
				System.out.println("User found: " + u.getName() + " " + u.getSurname());
				break;
			}
		}

		rs.close();
		statement.close();
		return u;
	}

	public Book getBook(int bookId) throws SQLException {
		Book b = null;

		String sqlQuery = "select * from book where idbook = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, bookId);

		ResultSet rs = preparedStatement.executeQuery();

		if (rs.next()) {
			String json = rs.getString("bookObj");
			Gson gson = new GsonBuilder().create();
			b = gson.fromJson(json, Book.class);
		}

		rs.close();
		preparedStatement.close();

		return b;
	}

	public boolean updateUser(User newUser, String oldUserObj) throws SQLException {
		String sqlQuery = "update user set userObj = ? where userObj = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

		Gson gson = new GsonBuilder().create();
		String userObj = gson.toJson(newUser);

		System.out.println("Old Object: " + oldUserObj);
		System.out.println("New Object: " + userObj);

		preparedStatement.setString(1, userObj);
		preparedStatement.setString(2, oldUserObj);

		boolean updated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();

		return updated;
	}

	public <T extends Object> T get(String table, int id, Class<T> type) {

		try {
			// System.out.println("All Books");

			Statement statement = connection.createStatement();
			String sqlQuery = "select * from " + table + " Where id" + table + " = " + id;

			ResultSet rs = statement.executeQuery(sqlQuery);

			while (rs.next()) {
				String json = rs.getString("bookObj");

				Gson gson = new GsonBuilder().create();
				return type.cast(gson.fromJson(json, type.getClass()));
			}

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}

		return null;
	}

	public void delete(String table, int id) {
		try {
			// System.out.println("All Books");

			Statement statement = connection.createStatement();
			String sqlQuery = "delete from " + table + " Where id" + table + " = " + id;
			System.out.println(sqlQuery);
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
	}
}
