package services;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBConnector;

/**
 * Servlet implementation class mainPageService
 */
@WebServlet("/index")
public class mainPageService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public mainPageService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Ich Gette tolle sachen ahaaaa");
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		ServletContext ctx = request.getServletContext();
		DBConnector db = (DBConnector) ctx.getAttribute("DBConnector");

		String button = request.getParameter("button");

		if (button == null) {
			System.out.println("Dubist falsch hier...aahhhaaa");
			return;
		}

		switch (button) {
		case "Update":
			// String temp = "{\\\"bookObj\\\":{\\\"title\\\":\\\"Herr der Ringe\\\",
			// \\\"Autor\\\":\\\"JRR Tolkien\\\"}}";
			String temp = "{\"bookObj\":{\"title\":\"Herr der Ringe2\", \"Autor\":\"JRR Tolkien\"}}";
			System.out.println("TEMP: " + temp);
			db.writeBookIntoDB(temp);
			break;
		case "Get":
			System.out.println("GEEEEEET");
			db.getAllBooks();
		default:
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Ich Poste tolle sachen ahaaaa");

		// doGet(request, response);
	}

}
