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

// 프론트 컨트롤러 적용
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	req.setAttribute("viewUrl", "/member/MemberForm.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int result = 0;
        
        try {
        	ServletContext sc = this.getServletContext();
            Member member = (Member) req.getAttribute("member");
            MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
            
            result = memberDao.insert(member);
            if (result != 1) {
            	throw new ServletException("Failed in insert, result:" + result);
            }
            
            req.setAttribute("viewUrl", "redirect:list.do");
            
        } catch (Exception e) {
        	throw new ServletException(e);
        }
    }
}
