package spms.servlets;

import java.io.IOException;

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
        int memberNo = Integer.parseInt(req.getParameter("no"));
        RequestDispatcher rd = null;
        
        try {
            ServletContext sc = this.getServletContext(); 
            MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
            
            Member member = memberDao.selectOne(memberNo);
            if (member != null) {
	            req.setAttribute("member", member);
	                
	            rd = req.getRequestDispatcher("/member/MemberUpdateForm.jsp");
	            rd.forward(req, resp);
            } else {
            	throw new ServletException("Failed in update - select member no:" + memberNo);
            }
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
        
        try {
            Member member = new Member()
            					.setNo(Integer.parseInt(req.getParameter("no")))
            					.setEmail(req.getParameter("email"))
            					.setName(req.getParameter("name"));

            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
            
            result = memberDao.update(member);
            if (result == 1) {
            	resp.sendRedirect("list");
            } else {
            	throw new Exception("Failed in update - update()");
            }
        } catch (Exception e) {
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error/Error.jsp");
            rd.forward(req, resp);
        }
    }
}
