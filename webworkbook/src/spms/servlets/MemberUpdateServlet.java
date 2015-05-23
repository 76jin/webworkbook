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

@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Connection conn = null;
        int memberNo = Integer.parseInt(req.getParameter("no"));
        RequestDispatcher rd = null;
        
        try {
            ServletContext sc = this.getServletContext(); 
            conn = (Connection) sc.getAttribute("conn");

            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);
            
            Member member = memberDao.selectOne(memberNo);
            
            req.setAttribute("member", member);
                
            rd = req.getRequestDispatcher("/member/MemberUpdateForm.jsp");
            rd.forward(req, resp);
//          throw new ServletException("Failed in update - select member no:" + no);
        } catch (Exception e) {
            req.setAttribute("error", e);
            rd = req.getRequestDispatcher("/error/Error.jsp");
            rd.forward(req, resp);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
    	int result = 0;
        Connection conn = null;
        
        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");
            
            Member member = new Member()
            					.setNo(Integer.parseInt(req.getParameter("no")))
            					.setEmail(req.getParameter("email"))
            					.setName(req.getParameter("name"));

            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);
            
            result = memberDao.update(member);
            if (result == 1) {
            	resp.sendRedirect("list");
            } else {
            	throw new Exception("Faile in update - update()");
            }
        } catch (Exception e) {
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error/Error.jsp");
            rd.forward(req, resp);
        }
    }
}
