package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import spms.annotation.Component;
import spms.vo.Member;

@Component("memberDao")
public class MySqlMemberDao implements MemberDao {
	SqlSessionFactory sqlSessionFactory;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	// 회원 목록
	public List<Member> selectList() throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			return sqlSession.selectList("spms.dao.MemberDao.selectMemberList");
		} finally {
			sqlSession.close();
		}
	}
	
	// 회원 등록
	public int insert(Member member) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			int result = sqlSession.insert("spms.dao.MemberDao.insert", member);
			sqlSession.commit();
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
	// 회원 상세 정보 조회
	public Member selectOne(int no) throws Exception {
		SqlSession sqlSession = null;
        try {
        	sqlSession = sqlSessionFactory.openSession();
        	return sqlSession.selectOne("spms.dao.MemberDao.selectMember", no);
        } finally {
        	sqlSession.close();
        }
	}
	
//	// 회원 정보 변경
//	public int update(Member member) throws Exception {
//		Connection connection = null;
//        PreparedStatement pstmt = null;
//        String query = "UPDATE MEMBERS SET"
//                + " EMAIL=?, MNAME=?, MOD_DATE=now() WHERE MNO=?";
//        int result = 0;
//        
//        try {
//        	connection = ds.getConnection();
//            pstmt = connection.prepareStatement(query);
//            pstmt.setString(1, member.getEmail());
//            pstmt.setString(2, member.getName());
//            pstmt.setInt(3, member.getNo());
//            result = pstmt.executeUpdate();
//            
//            return result;
//        } catch (Exception e) {
//        	throw new Exception(e);
//        } finally {
//            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
//			try { if (connection != null) connection.close(); } catch (Exception e) {}
//        }
//	}
//	
//	// 회원 삭제
//	public int delete(int no) throws Exception {
//		Connection connection = null;
//        PreparedStatement pstmt = null;
//        String query = "DELETE FROM MEMBERS WHERE MNO=?";
//        int result = 0;
//        
//        try {
//        	connection = ds.getConnection();
//            pstmt = connection.prepareStatement(query);
//            pstmt.setInt(1, no);
//            result = pstmt.executeUpdate();
//            
//            return result;
//            
//        } catch (Exception e) {
//        	throw new Exception(e);
//        } finally {
//            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
//			try { if (connection != null) connection.close(); } catch (Exception e) {}
//        }
//	}
//	
//	// 있으면 Member 객체 리턴, 없으면 null 리턴
//	public Member exist(String email, String password) throws Exception {
//		Connection connection = null;
//	    PreparedStatement pstmt = null;
//	    ResultSet rs = null;
//	    String query = "SELECT MNAME,EMAIL FROM MEMBERS WHERE EMAIL=? AND PWD=?";
//	    
//	    try {
//        	connection = ds.getConnection();
//	        pstmt = connection.prepareStatement(query);
//	        pstmt.setString(1, email);
//	        pstmt.setString(2, password);
//	        
//	        rs = pstmt.executeQuery();
//	        if (rs.next()) {   // 로그인이 성공한 경우
//	            Member member = new Member()
//	                                .setEmail(rs.getString("EMAIL"))
//	                                .setName(rs.getString("MNAME"));
//	            
//	            return member;
//	        } else {           // 로그인이 실패한 경우
//	        	return null;
//	        }
//        } catch (Exception e) {
//            throw new ServletException(e);
//        } finally {
//            try { if (rs != null) rs.close(); } catch (Exception e) {}
//            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
//			try { if (connection != null) connection.close(); } catch (Exception e) {}
//        }
//	}
//	


	@Override
	public int delete(int no) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Member member) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Member exist(String email, String password) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
