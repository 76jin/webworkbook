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
import spms.vo.Member;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        
        RequestDispatcher rd = req.getRequestDispatcher("/member/MemberAdd.jsp");
        rd.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection conn = null;
        
        try {
        	int result = 0;
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");
            
            Member member = new Member()
            					.setEmail(req.getParameter("email"))
            					.setPassword(req.getParameter("password"))
            					.setName(req.getParameter("name"));

            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);
            
            result = memberDao.insert(member);
            if (result == 0)
            	throw new Exception("Insert failed!");
            
            // redirect
            resp.sendRedirect("list");
            
        } catch (Exception e) {
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error/Error.jsp");
            rd.forward(req, resp);
        }
    }
}
