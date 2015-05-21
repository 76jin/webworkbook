package spms.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection conn = null;
        RequestDispatcher rd = null;
        
        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");
            
            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);
            
            req.setAttribute("members", memberDao.selectList());
            
            resp.setContentType("text/html; charset=UTF-8");
            
            rd = req.getRequestDispatcher("/member/MemberList.jsp");
            rd.include(req, resp);
            
        } catch (Exception e) {
            req.setAttribute("error", e);
            rd = req.getRequestDispatcher("/error/Error.jsp");
            rd.forward(req, resp);
        }
    }

}
