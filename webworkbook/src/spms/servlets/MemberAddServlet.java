package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>회원 등록</title></head>");
        out.println("<body>");
        out.println("<h1>회원 등록</h1>");
        out.println("<form action='add' method='post'>");
        out.println("    이름: <input type='text' name='name'><br>");
        out.println("    이메일: <input type='email' name='email'><br>");
        out.println("    암호: <input type='password' name='password'><br>");
        out.println("    <input type='submit' value='추가'>");
        out.println("    <input type='reset' value='취소'><br>");
        out.println("</form>");
        out.println("</body></html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");  // POST 요청시 한글 깨침 처리.
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = "insert into MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
                + " values(?,?,?,now(),now())";
        
        try {
//          DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));
            
            conn = DriverManager.getConnection(
                    sc.getInitParameter("url"),
                    sc.getInitParameter("username"),
                    sc.getInitParameter("password"));
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, req.getParameter("email"));
            pstmt.setString(2, req.getParameter("password"));
            pstmt.setString(3, req.getParameter("name"));
            pstmt.executeUpdate();
            
            // redirect
            resp.sendRedirect("list");
            
            // redirect 하면, 아래 코드는 웹브라우저에게 보내지지 않는다.
            /*
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>회원등록 결과</title>");
//          out.println("<meta http-equiv='Refresh' Content='1;url=list'>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <p>등록 성공입니다!</p>");
            out.println("</body>");
            out.println("</html>");
            
            resp.setHeader("Refresh", "1;url=list");
//          resp.addHeader("Refresh", "1;url=list");
            */
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        } finally {
            try { conn.close(); } catch (SQLException e) {}
            try { pstmt.close(); } catch (SQLException e) {}
        }
    }
}
