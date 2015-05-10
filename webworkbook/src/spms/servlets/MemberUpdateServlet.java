package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MemberUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "select MNO,EMAIL,MNAME,CRE_DATE from MEMBERS"
                + " where MNO=" + req.getParameter("no");
        
        try {
            ServletContext sc = this.getServletContext(); 
            Class.forName(sc.getInitParameter("driver"));
            conn = DriverManager.getConnection(
                    sc.getInitParameter("url"), 
                    sc.getInitParameter("username"), 
                    sc.getInitParameter("password"));
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>회원정보</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <h1>회원정보</h1>");
            out.println("    <form action='update' method='post'>");
            out.println("        번호: <input type='text' name='no' value='" + 
                                req.getParameter("no") + "' readonly><br>");
            out.println("        이름: <input type='text' name='name' value='" +
                                rs.getString("MNAME") + "'><br>");
            out.println("        이메일: <input type='email' name='email'" +
                                " value='" + rs.getString("EMAIL") + "'><br>");
            out.println("        가입일: " + rs.getString("CRE_DATE") + "<br>");
            out.println("        <input type='submit' value='저장'>");
            out.println("        <input type='reset' value='취소' onclick='location.href=\"list\"'><br>");
            out.println("    </form>");
            out.println("</body>");
            out.println("</html>");
            
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            try { rs.close(); } catch (Exception e) {}
            try { stmt.close(); } catch (Exception e) {}
            try { conn.close(); } catch (Exception e) {}
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE MEMBERS SET"
                + " EMAIL=?, MNAME=?, MOD_DATE=now() WHERE MNO=?";
        
        try {
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));
            conn = DriverManager.getConnection(
                    sc.getInitParameter("url"),
                    sc.getInitParameter("username"),
                    sc.getInitParameter("password"));
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, req.getParameter("email"));
            pstmt.setString(2, req.getParameter("name"));
            pstmt.setInt(3, Integer.parseInt(req.getParameter("no")));
            pstmt.executeUpdate();
            
            resp.sendRedirect("list");
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            try { pstmt.close(); } catch (Exception e) {}
            try { conn.close(); } catch (Exception e) {}
        }
    }
}
