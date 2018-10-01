package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import db.DBConnector;

public class MainBeanListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ServletContext ctx = servletContextEvent.getServletContext();
		DBConnector db = (DBConnector) ctx.getAttribute("DBConnector");
		db.closeDBConnection();
		System.out.println("Closing DB Connection");
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		ServletContext ctx = servletContextEvent.getServletContext();

		System.out.println("Starting DB Connectivity");
		DBConnector db = new DBConnector();
		boolean connected = db.connectToMySQL("localhost:3306", "library", "root", "root");
		if (connected)
			System.out.println("Finished DB Connectivity: Connected");
		else
			System.out.println("Finished DB Connectivity: Not Connected");
		ctx.setAttribute("DBConnector", db);

	}

}
