package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	private int id;

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

		System.out.println("Ich gette: Customer");

		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("userName");

		String name = username.substring(0, username.indexOf(" "));
		System.out.println("The name is: " + name);

		try {
			Map.Entry<Integer, User> uId = db.getUser(name);
			if (uId == null) {
				// TODO Add new user in database
				System.out.println("No user found!");
				return;
			}
			u = uId.getValue();
			id = uId.getKey();

			session.setAttribute("fullname", u.getName() + " " + u.getSurname());
			HashMap<Integer, Book> mapIds = (HashMap<Integer, Book>) db.listBorrowedBooks(u);
			HashMap<Integer, Book> mapAvailable = (HashMap<Integer, Book>) db.listAvailableBooks();

			request.setAttribute("mapIds", mapIds);
			request.setAttribute("mapAvailable", mapAvailable);

			RequestDispatcher dispatcher = request.getRequestDispatcher("customerView.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String button = request.getParameter("button");
		System.out.println("Button: " + button);

		if (button != null)
			switch (button) {
			case "Return Book":
				int bookId = Integer.parseInt(request.getParameter("id"));
				System.out.println("Returning a book with id: " + bookId);
//				Gson gson = new GsonBuilder().create();
//				String oldUserObj = gson.toJson(u);
				u.getBookIds().remove(Integer.valueOf(bookId));
				try {
					db.updateUser(u, id);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "Borrow Book":
				int addId = Integer.parseInt(request.getParameter("addId"));
				System.out.println("AddID: " + addId);
				try {
					if (db.getBook(addId) != null) {
						System.out.println("Borrowing Book with id: " + addId);
						if (u.getBookIds() != null)
							u.getBookIds().add(addId);
						else
							u.setBookIds(Arrays.asList(addId));
						db.updateUser(u, id);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}

		response.sendRedirect(request.getRequestURL().toString());
//		if (request.getParameter("id") != "") {
//			int bookId = Integer.parseInt(request.getParameter("id"));
//			System.out.println("Returning a book with id: " + bookId);
//			Gson gson = new GsonBuilder().create();
//			String oldUserObj = gson.toJson(u);
//			u.getBookIds().remove(Integer.valueOf(bookId));
//			try {
//				db.updateUser(u, oldUserObj);
//				response.sendRedirect(request.getRequestURL().toString());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}

//		if (!request.getParameter("addId").isEmpty()) {
//			int addId = Integer.parseInt(request.getParameter("addId"));
//			System.out.println("AddID: " + addId);
//			try {
//				if (db.getBook(addId) != null) {
//					System.out.println("Borrowing Book with id: " + addId);
//					u.getBookIds().add(addId);
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		// doGet(request, response);
	}

}
