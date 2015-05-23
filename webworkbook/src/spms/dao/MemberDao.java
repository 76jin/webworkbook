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
	
	// 회원 삭제
	
	// 회원 상세 정보 조회
	
	// 회원 정보 변경
	
	// 있으면 Member 객체 리턴, 없으면 null 리턴
	
}
