package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

//    private Logger logger = Logger.getLogger(getClass());
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM MEMBERS WHERE MNO=?";
        int result = 0;
        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(req.getParameter("no")));
            result = pstmt.executeUpdate();
//          logger.info("[delete] no:" + result);   // 0: delete fail.
            
            resp.sendRedirect("list");
            
        } catch (Exception e) {
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error/Error.jsp");
            rd.forward(req, resp);
        } finally {
            try { pstmt.close(); } catch (Exception e) {}
//          try { conn.close(); } catch (Exception e) {}
        }
    }
    
}
