package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import spms.vo.Member;

public class MemberDao {
	Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	// 회원 목록
	public List<Member> selectList() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		
		String query =
				"SELECT MNO,MNAME,EMAIL,CRE_DATE"
				+ " FROM MEMBERS"
				+ " order by MNO asc";
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			
			ArrayList<Member> members = new ArrayList<Member>();
			
			while (rs.next()) {
				members.add(new Member()
								.setNo(rs.getInt("MNO"))
								.setName(rs.getString("MNAME"))
								.setEmail(rs.getString("EMAIL"))
								.setCreatedDate(rs.getDate("CRE_DATE")));
			}
			return members;
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {}
			try { if (stmt != null) stmt.close(); } catch (Exception e) {}
		}
	}
	
	// 회원 등록
	public int insert(Member member) throws Exception {
        PreparedStatement pstmt = null;
        String query = "insert into MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
                + " values(?,?,?,now(),now())";
        
        try {
        	int result = 0;
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            
            result = pstmt.executeUpdate();
            
            return result;
        } catch (Exception e) {
        	throw new Exception(e);
        } finally {
            try { pstmt.close(); } catch (SQLException e) {}
        }
	}
	
	// 회원 상세 정보 조회
	public Member selectOne(int no) throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        String query = "select MNO,EMAIL,MNAME,CRE_DATE from MEMBERS"
                + " where MNO=" + no;
        
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                Member member = new Member()
                                    .setNo(no)
                                    .setEmail(rs.getString("EMAIL"))
                                    .setName(rs.getString("MNAME"))
                                    .setCreatedDate(rs.getDate("CRE_DATE"));
                return member;
            } else {
                throw new ServletException("Failed in update - select member no:" + no);
            }
        } catch (Exception e) {
        	throw new Exception(e);
        } finally {
            try { rs.close(); } catch (Exception e) {}
            try { stmt.close(); } catch (Exception e) {}
        }
	}
	
	// 회원 정보 변경
	public int update(Member member) throws Exception {
		int result = 0;
        PreparedStatement pstmt = null;
        String query = "UPDATE MEMBERS SET"
                + " EMAIL=?, MNAME=?, MOD_DATE=now() WHERE MNO=?";
        
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getName());
            pstmt.setInt(3, member.getNo());
            result = pstmt.executeUpdate();
            
            return result;
        } catch (Exception e) {
        	throw new Exception(e);
        } finally {
            try { pstmt.close(); } catch (Exception e) {}
        }
	}
	
	// 회원 삭제
	public int delete(int no) throws Exception {
        PreparedStatement pstmt = null;
        String query = "DELETE FROM MEMBERS WHERE MNO=?";
        int result = 0;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, no);
            result = pstmt.executeUpdate();
            
            return result;
            
        } catch (Exception e) {
        	throw new Exception(e);
        } finally {
            try { pstmt.close(); } catch (Exception e) {}
        }
	}
	
	// 있으면 Member 객체 리턴, 없으면 null 리턴
	public Member exist(String email, String password) throws Exception {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String query = "SELECT MNAME,EMAIL FROM MEMBERS WHERE EMAIL=? AND PWD=?";
	    
	    try {
	        pstmt = connection.prepareStatement(query);
	        pstmt.setString(1, email);
	        pstmt.setString(2, password);
	        
	        rs = pstmt.executeQuery();
	        if (rs.next()) {   // 로그인이 성공한 경우
	            Member member = new Member()
	                                .setEmail(rs.getString("EMAIL"))
	                                .setName(rs.getString("MNAME"));
	            
	            return member;
	        } else {           // 로그인이 실패한 경우
	        	return null;
	        }
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
        }
	}
	
}
