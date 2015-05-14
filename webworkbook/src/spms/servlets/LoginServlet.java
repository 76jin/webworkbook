package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String query = "SELECT MNAME,EMAIL FROM MEMBERS WHERE EMAIL=? AND PWD=?";
	    final String URL_MEMBER_LIST = "../member/list";
	    final String URL_LOGIN_FAIL = "/auth/LoginFail.jsp"; 
	    
	    try {
	        ServletContext sc = request.getServletContext();
	        conn = (Connection) sc.getAttribute("conn");
	        
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, request.getParameter("email"));
	        pstmt.setString(2, request.getParameter("password"));
	        
	        rs = pstmt.executeQuery();
	        if (rs.next()) {   // 로그인이 성공한 경우
	            Member member = new Member()
	                                .setEmail(rs.getString("EMAIL"))
	                                .setName(rs.getString("MNAME"));
	            HttpSession session = request.getSession();
	            session.setAttribute("member", member);
	            
	            response.sendRedirect(URL_MEMBER_LIST);
	        } else {           // 로그인이 실패한 경우
	            RequestDispatcher rd = request.getRequestDispatcher(URL_LOGIN_FAIL);
	            rd.forward(request, response);
	        }
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
        }
	}

}
