package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        // CharacterEncodingFilter 에서 처리
//      req.setCharacterEncoding("UTF-8");  // POST 요청시 한글 깨침 처리.
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "insert into MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
                + " values(?,?,?,now(),now())";
        
        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");
            
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, req.getParameter("email"));
            pstmt.setString(2, req.getParameter("password"));
            pstmt.setString(3, req.getParameter("name"));
            pstmt.executeUpdate();
            
            // redirect
            resp.sendRedirect("list");
            
        } catch (Exception e) {
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error/Error.jsp");
            rd.forward(req, resp);
        } finally {
            try { pstmt.close(); } catch (SQLException e) {}
//          try { conn.close(); } catch (SQLException e) {}
        }
    }
}
