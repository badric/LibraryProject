package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBConnector;
import model.Book;
import model.User;

/**
 * Servlet implementation class employeePageService
 */

public class employeePageService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public employeePageService() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ich Gette tolle sachen ahaaaa");
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		ServletContext ctx = request.getServletContext();
		DBConnector db = (DBConnector) ctx.getAttribute("DBConnector");
		
		Map<Integer,Book> books = new HashMap<Integer,Book>();
		Map<Integer, User> users = new HashMap<Integer,User>();

		request.setAttribute("Submit", null);
		
		books = db.getAllBooks();
		users = db.getAllUsers();
		
		System.out.println("Get all books");
		try {
			request.setAttribute("books", books);
			request.setAttribute("users", users);
			//response.sendRedirect("http://localhost:8080/employee");
			RequestDispatcher dispatcher = request.getRequestDispatcher("employeeView.jsp");
		    dispatcher.forward(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//response.sendRedirect("http://localhost:8080/employee");
//		response.setStatus(HttpServletResponse.SC_FOUND);
//		response.setHeader("Location", "http://localhost:8080/employee");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ich Poste tolle sachen ahaaaa");
		ServletContext ctx = request.getServletContext();
		DBConnector db = (DBConnector) ctx.getAttribute("DBConnector");
		
		Map<Integer,Book> books = new HashMap<Integer,Book>();
		Map<Integer, User> users = new HashMap<Integer,User>();
		
		String button = request.getParameter("Submit");

		System.out.println("Button: "+ button);
		
		if (button != null) 
		switch (button) {
		case "Submit New Book":
			System.out.println("Erstelle neues Buch!");
			String title = request.getParameter("title");
			String autor = request.getParameter("autor");
			String year = request.getParameter("year");
			String age = request.getParameter("age");
			String isbn = request.getParameter("isbn");
			String price = request.getParameter("price");
			System.out.println("{\"title\":\""+title+"\", \"autor\":\""+autor+"\", \"year\":\""+year+"\", \"age\":\""+age+"\", \"isbn\":\""+isbn+"\", \"price\":\""+price+"\"}");
			String newBook = "{\"title\":\""+title+"\", \"autor\":\""+autor+"\", \"year\":\""+year+"\", \"age\":\""+age+"\", \"isbn\":\""+isbn+"\", \"price\":\""+price+"\"}";
			db.writeBookIntoDB(newBook);
			break;
		case "Submit New User":
			System.out.println("Erstelle neues Buch!");
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String userAge = request.getParameter("age");
			System.out.println("{\"name\":\""+name+"\", \"surname\":\""+surname+"\", \"age\":\""+userAge+"\"}");
			String newUser = "{\"name\":\""+name+"\", \"surname\":\""+surname+"\", \"age\":\""+userAge+"\"}";
			db.writeUserIntoDB(newUser);
			break;
		case "Delete Book":
			int id = Integer.parseInt(request.getParameter("bookID"));
			db.delete("book", id);
			break;
		case "Delete User":
			id = Integer.parseInt(request.getParameter("userID"));
			db.delete("User", id);
			System.out.println("Trying to delete User " +id);
break;
		case "Get":
			books = db.getAllBooks();
			request.setAttribute("books", books);
			request.getRequestDispatcher("employeeView.jsp").forward(request, response);
		default:
			return;
		}
		
		request.setAttribute("Submit", null);
		
		books = db.getAllBooks();
		users = db.getAllUsers();
		

		try {
			request.setAttribute("books", books);
			request.setAttribute("users", users);
			
			response.sendRedirect("http://localhost:8080/LibraryProject/employee");
			
//			RequestDispatcher dispatcher = request.getRequestDispatcher("employeeView.jsp");
//		    dispatcher.forward(request, response);
			
		}catch(Exception e) {
			
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//doGet(request, response);
	}

	
	
	
}
