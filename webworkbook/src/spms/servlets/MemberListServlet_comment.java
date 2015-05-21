package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;

@WebServlet("/member/list2")
public class MemberListServlet_comment extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        RequestDispatcher rd = null;
        String query = "select MNO,MNAME,EMAIL,CRE_DATE"
                + " from MEMBERS"
                + " order by MNO ASC";
        try {
            // 1. 사용할 JDBC 드라이버를 등록하라.
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//            ServletConfig config = this.getServletConfig();
//            Class.forName(config.getInitParameter("driver"));
//            Class.forName(sc.getInitParameter("driver"));
            
            // 2. 드라이버를 사용하여 MySQL 서버와 연결하라.
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");
            
            // 3. 커넥션 객체로부터 SQL을 던질 객체를 준비하라.
            stmt = conn.createStatement();
            
            // 4. SQL을 던지는 객체를 사용하여 서버에 질의하라.
            rs = stmt.executeQuery(query);
            
            // 5. 서버에서 가져온 데이터를 사용하여 HTML을 만들어서 웹 브라우저로 출력하라.
            // 5. 데이터베이스에서 회원 정보를 가져와 Member 에 담는다.
            //    그리고, Member 객체를 ArrayList 에 추가한다.
            resp.setContentType("text/html; charset=UTF-8");
            ArrayList<Member> members = new ArrayList<Member>();
            
            while (rs.next()) {
                members.add(new Member()
                                .setNo(rs.getInt("MNO"))
                                .setName(rs.getString("MNAME"))
                                .setEmail(rs.getString("EMAIL"))
                                .setCreatedDate(rs.getDate("CRE_DATE"))
                        );
            }
            
            // request 에 회원 목록 데이터를 보관한다.
            req.setAttribute("members", members);
            
            // JSP로 출력을 위임한다.
            rd = req.getRequestDispatcher("/member/MemberList.jsp");
            rd.include(req, resp);
            
        } catch (SQLException e) {
            req.setAttribute("error", e);
            rd = req.getRequestDispatcher("/error/Error.jsp");
            rd.forward(req, resp);
        } finally {
            // 6. 자원 해제.
            try {rs.close();} catch (Exception e) {}
            try {stmt.close();} catch (Exception e) {}
//          try {conn.close();} catch (Exception e) {}
        }
        
    }

}
