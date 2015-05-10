package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(getClass());
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM MEMBERS WHERE MNO=?";
        int result = 0;
        try {
            Class.forName(this.getServletContext().getInitParameter("driver"));
            conn = DriverManager.getConnection(
                    this.getServletContext().getInitParameter("url"),
                    this.getServletContext().getInitParameter("username"),
                    this.getServletContext().getInitParameter("password"));
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(req.getParameter("no")));
            result = pstmt.executeUpdate();
            
            logger.info("[delete] no:" + result);
            resp.sendRedirect("list");
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            try { pstmt.close(); } catch (Exception e) {}
            try { conn.close(); } catch (Exception e) {}
        }
    }
    
}
