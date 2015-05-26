package spms.servlets;

import java.io.IOException;

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
        
        try {
            ServletContext sc = this.getServletContext(); 
            MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
            
            Member member = memberDao.selectOne(memberNo);
            if (member == null) {
            	throw new ServletException("Failed in update, member is null - select member no:" + memberNo);
            }
            
	        req.setAttribute("member", member);
	        req.setAttribute("viewUrl", "/member/MemberUpdateForm.jsp");
        } catch (Exception e) {
        	throw new ServletException(e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
    	int result = 0;
        
        try {
        	ServletContext sc = this.getServletContext();
            Member member = (Member) req.getAttribute("member");
            MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
            
            result = memberDao.update(member);
            if (result != 1) {
            	throw new ServletException("Failed in update, result:" + result);
            }
            
            req.setAttribute("viewUrl", "redirect:list.do");
        } catch (Exception e) {
        	throw new ServletException(e);
        }
    }
}
