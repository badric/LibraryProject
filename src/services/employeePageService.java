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
 * Servlet implementation class employeePageService
 */
@WebServlet("/employeePageService")
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

		String button = request.getParameter("bt");

		if (button == null) {
			System.out.println("Dubist falsch hier...aahhhaaa");
			return;
		}

		switch (button) {
		case "Update":
			//String temp = "{\"bookObj\":{\"title\":\"Herr der Ringe2\", \"Autor\":\"JRR Tolkien\"}}";
//			System.out.println("TEMP: " + temp);
//			db.writeBookIntoDB(temp);
			break;
		case "Get":
			db.getAllBooks();
		default:
			return;
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ich Poste tolle sachen ahaaaa");
		doGet(request, response);
	}

	
	
	
}
