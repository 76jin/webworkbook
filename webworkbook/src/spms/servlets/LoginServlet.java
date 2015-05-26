package spms.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setAttribute("viewUrl", "/auth/LoginForm.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    
	    try {
	        ServletContext sc = request.getServletContext();
	        MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
	        
	        Member member = memberDao.exist(email, password);
	        if (member != null) {
	            HttpSession session = request.getSession();
	            session.setAttribute("member", member);
	            
	            request.setAttribute("viewUrl", 
	            		"redirect:" + request.getContextPath() + "/member/list.do");
	        } else {           // 로그인이 실패한 경우
	        	request.setAttribute("viewUrl", "/auth/LoginFail.jsp");
	        }
        } catch (Exception e) {
        	throw new ServletException(e);
        }
	}

}
