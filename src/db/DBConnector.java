package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.Vector;
import com.google.gson.*;

public class DBConnector {

	Connection connection;

	public boolean connectToMySQL(String host, String database, String user, String pw) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
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

	public boolean getAllBooks() {
		try {
			System.out.println("All Books");

			Statement statement = connection.createStatement();
			String sqlQuery = "select * from book";

			ResultSet rs = statement.executeQuery(sqlQuery);
			while (rs.next()) {
				String json = rs.getString("bookObj");
				System.out.println("JSON: " + json);
				Gson gson = new GsonBuilder().create();
				Book b = gson.fromJson(json, Book.class);
				System.out.println(b.getTitle());
			}

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}

		return false;
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

	public class Book {

		BookObj bookObj;
		// int id;

		Book() {

		}

		Book(BookObj bookObj) {
			this.bookObj = bookObj;
		}

		public String getTitle() {
			return bookObj.getTitle();
		}
		
		public String getAutor() {
			return bookObj.getAutor();
		}
	}
	
	public class BookObj {

		String title, autor;
		// int id;

		BookObj() {

		}

		BookObj(String title, String autor) {
			this.title = title;

			this.autor = autor;
		}

		public String getTitle() {
			return title;
		}
		
		public String getAutor() {
			return autor;
		}
	}
}
