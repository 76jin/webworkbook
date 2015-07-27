package spms.dao;

import java.util.List;

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
	
	// 회원 정보 변경
	public int update(Member member) throws Exception {
		SqlSession sqlSession = null;
        
        try {
        	sqlSession = sqlSessionFactory.openSession();
            int result = sqlSession.update("spms.dao.MemberDao.update", member);
            sqlSession.commit();
            return result;
        } finally {
        	sqlSession.close();
        }
	}
	
	// 회원 삭제
	public int delete(int no) throws Exception {
		SqlSession sqlSession = null;
        
        try {
        	sqlSession = sqlSessionFactory.openSession();
        	int result = sqlSession.delete("spms.dao.MemberDao.delete", no);
        	sqlSession.commit();
        	return result;
        } finally {
        	sqlSession.close();
        }
	}
	
	// 있으면 Member 객체 리턴, 없으면 null 리턴
	public Member exist(String email, String password) throws Exception {
		SqlSession sqlSession = null;
	    
	    try {
	    	sqlSession = sqlSessionFactory.openSession();
	    	Member param = new Member().setEmail(email).setPassword(password);
	    	Member member = sqlSession.selectOne("spms.dao.MemberDao.exist", param);
	    	
	    	
	    	if (member != null && member.getEmail().length() > 0) { 	// 로그인이 성공한 경우 
	    		System.out.println("Function exist() no:" + member.getNo());
	            return member;
	        } else {           			// 로그인이 실패한 경우
	        	return null;
	        }
        } finally {
        	sqlSession.close();
        }
	}

}
