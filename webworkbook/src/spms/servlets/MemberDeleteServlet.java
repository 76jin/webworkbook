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

import spms.dao.MemberDao;

@SuppressWarnings("serial")
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

//    private Logger logger = Logger.getLogger(getClass());
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	int result = 0;
    	int no = Integer.parseInt(req.getParameter("no"));
        Connection conn = null;
        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");
            
            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);
            
            result = memberDao.delete(no);
            if (result == 1) {
            	resp.sendRedirect("list");
            } else {
            	throw new Exception("Failed delete - delete no:" + no);
            }
            
        } catch (Exception e) {
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error/Error.jsp");
            rd.forward(req, resp);
        }
    }
    
}
