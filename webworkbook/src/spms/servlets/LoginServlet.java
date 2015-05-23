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
	    RequestDispatcher rd = request.getRequestDispatcher("/auth/LoginForm.jsp");
	    rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Connection conn = null;
	    final String URL_MEMBER_LIST = "../member/list";
	    final String URL_LOGIN_FAIL = "/auth/LoginFail.jsp";
	    
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    
	    try {
	        ServletContext sc = request.getServletContext();
	        conn = (Connection) sc.getAttribute("conn");
	        
	        MemberDao memberDao = new MemberDao();
	        memberDao.setConnection(conn);
	        
	        Member member = memberDao.exist(email, password);
	        if (member != null) {
	            HttpSession session = request.getSession();
	            session.setAttribute("member", member);
	            
	            response.sendRedirect(URL_MEMBER_LIST);
	        } else {           // 로그인이 실패한 경우
	            RequestDispatcher rd = request.getRequestDispatcher(URL_LOGIN_FAIL);
	            rd.forward(request, response);
	        }
        } catch (Exception e) {
            throw new ServletException(e);
        }
	}

}
