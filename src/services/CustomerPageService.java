package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import db.DBConnector;
import model.Book;
import model.User;

/**
 * Servlet implementation class CustomerPageService
 */

public class CustomerPageService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnector db;
	private User u;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerPageService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		db = (DBConnector) getServletContext().getAttribute("DBConnector");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// String action = request.getServletPath();
		System.out.println("Ich gette: Customer");

		try {
			listAllBorrowedBooks(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void listAllBorrowedBooks(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("userName");

		String name = username.substring(0, username.indexOf(" "));
		System.out.println("The name is: " + name);
		u = db.getUser(name);

		if (u == null) {
			// TODO Add new user in database
			System.out.println("No user found!");
			return;
		}

		// List<Book> books = new ArrayList<>();
		HashMap<Book, Integer> mapIds = new HashMap<>();
		List<Integer> bookIds = u.getBookIds();
		for (int id : bookIds) {
			Book b = db.getBook(id);
			// books.add(b);
			mapIds.put(b, id);
		}
		// request.setAttribute("listBook", books);
		request.setAttribute("mapIds", mapIds);
		RequestDispatcher dispatcher = request.getRequestDispatcher("customerView.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int bookId = Integer.parseInt(request.getParameter("id"));
		System.out.println("BookID: " + bookId);
		if (bookId > 0) {
			System.out.println("Returning a book with id: " + bookId);
			Gson gson = new GsonBuilder().create();
			String oldUserObj = gson.toJson(u);
			u.getBookIds().remove(Integer.valueOf(bookId));
			try {
				db.updateUser(u, oldUserObj);
				response.sendRedirect(request.getRequestURL().toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// doGet(request, response);
	}

}
