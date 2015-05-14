package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;

@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int memberNo = Integer.parseInt(req.getParameter("no"));
        String query = "select MNO,EMAIL,MNAME,CRE_DATE from MEMBERS"
                + " where MNO=" + memberNo;
        RequestDispatcher rd = null;
        
        try {
            ServletContext sc = this.getServletContext(); 
            conn = (Connection) sc.getAttribute("conn");
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                Member member = new Member()
                                    .setNo(memberNo)
                                    .setEmail(rs.getString("EMAIL"))
                                    .setName(rs.getString("MNAME"))
                                    .setCreatedDate(rs.getDate("CRE_DATE"));
                req.setAttribute("member", member);
                
                rd = req.getRequestDispatcher("/member/MemberUpdateForm.jsp");
                rd.forward(req, resp);
            } else {
                throw new ServletException();
            }
        } catch (Exception e) {
            req.setAttribute("error", e);
            rd = req.getRequestDispatcher("/error/Error.jsp");
            rd.forward(req, resp);
        } finally {
            try { rs.close(); } catch (Exception e) {}
            try { stmt.close(); } catch (Exception e) {}
//          try { conn.close(); } catch (Exception e) {}
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//      CharacterEncodingFilter 에서 처리
//      req.setCharacterEncoding("UTF-8");
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE MEMBERS SET"
                + " EMAIL=?, MNAME=?, MOD_DATE=now() WHERE MNO=?";
        
        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");
            
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, req.getParameter("email"));
            pstmt.setString(2, req.getParameter("name"));
            pstmt.setInt(3, Integer.parseInt(req.getParameter("no")));
            pstmt.executeUpdate();
            
            resp.sendRedirect("list");
        } catch (Exception e) {
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error/Error.jsp");
            rd.forward(req, resp);
        } finally {
            try { pstmt.close(); } catch (Exception e) {}
//          try { conn.close(); } catch (Exception e) {}
        }
    }
}
