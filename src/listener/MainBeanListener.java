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
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		
		ServletContext ctx = servletContextEvent.getServletContext();
		
		System.out.println("Starting DB Connectivity");
		DBConnector db = new DBConnector();
		db.connectToMySQL("localhost", "library", "root", "");
		System.out.println("Finished DB Connectivity");
		
		ctx.setAttribute("DBConnector", db);
		
	}

}
