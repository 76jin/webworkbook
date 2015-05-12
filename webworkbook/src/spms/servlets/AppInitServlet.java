package spms.servlets;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

@WebServlet("/appInit")
public class AppInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass());
       
    public AppInitServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
	    logger.info(getClass().getName() + " 준비...");
	    super.init(config);
	    
	    try {
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));
            Connection conn = DriverManager.getConnection(
                    sc.getInitParameter("url"), 
                    sc.getInitParameter("username"), 
                    sc.getInitParameter("password"));
            sc.setAttribute("conn", conn);
        } catch (Exception e) {
            throw new ServletException(e);
        }
	}

	public void destroy() {
	    logger.info(getClass().getName() + " 마무리...");
	    super.destroy();
	    Connection conn = (Connection) this.getServletContext().getAttribute("conn");
	    try {
            if (conn != null && conn.isClosed() == false)
                conn.close();
        } catch (Exception e) {}
	}

}
