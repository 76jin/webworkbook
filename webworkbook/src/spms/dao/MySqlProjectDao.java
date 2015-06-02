package spms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.annotation.Component;
import spms.vo.Project;

@Component("projectDao")
public class MySqlProjectDao implements ProjectDao {
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Project> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String query =
				"SELECT PNO, PNAME, STA_DATE, END_DATE, STATE"
				+ " FROM PROJECTS"
				+ " order by PNO desc;";
		
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			
			ArrayList<Project> projects = new ArrayList<Project>();
			
			while (rs.next()) {
				projects.add(new Project()
								.setNo(rs.getInt("PNO"))
								.setTitle(rs.getString("PNAME"))
								.setStartDate(rs.getDate("STA_DATE"))
								.setEndDate(rs.getDate("END_DATE"))
								.setState(rs.getInt("STATE")));
			}
			return projects;
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {}
			try { if (stmt != null) stmt.close(); } catch (Exception e) {}
			try { if (connection != null) connection.close(); } catch (Exception e) {}
		}
	}

	@Override
	public int insert(Project project) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		String query = 
				"INSERT INTO PROJECTS" +
				" (PNAME,CONTENT,STA_DATE,END_DATE,CRE_DATE,TAGS)" + 
				" VALUES" +
				" (?, ?, ?, ?, now(), ?)";
		int result = 0;
		try {
			connection = ds.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, project.getTitle());
			pstmt.setString(2, project.getContent());
			pstmt.setDate(3, (Date) project.getStartDate());
			pstmt.setDate(4, (Date) project.getEndDate());
			pstmt.setString(5, project.getTags());
			
			result = pstmt.executeUpdate();
			System.out.println("result: " + result);
			return result;
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
			try { if (connection != null) connection.close(); } catch (Exception e) {}
		}
	}

	@Override
	public Project selectOne(int no) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from PROJECTS where PNO = ?";

		try {
			connection = ds.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return new Project(
						rs.getInt("PNO"), 
						rs.getString("PNAME"), 
						rs.getString("CONTENT"),
						rs.getDate("STA_DATE"),
						rs.getDate("END_DATE"),
						rs.getInt("STATE"),
						rs.getString("TAGS"));
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {}
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
			try { if (connection != null) connection.close(); } catch (Exception e) {}
		}
	}

	@Override
	public int update(Project project) throws Exception {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		String query = 
				"UPDATE PROJECTS" +
				" SET" +
				" PNAME = ?," +
				" CONTENT = ?," +
				" STA_DATE = ?," +
				" END_DATE = ?," +
				" TAGS = ?," +
				" STATE = ?" +
				" WHERE PNO = ?";
		
		try {
			connection = ds.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, project.getTitle());
			pstmt.setString(2, project.getContent());
			pstmt.setDate(3, (Date) project.getStartDate());
			pstmt.setDate(4, (Date) project.getEndDate());
			pstmt.setString(5, project.getTags());
			pstmt.setInt(6, project.getState());
			pstmt.setInt(7, project.getNo());
			
			result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
			try { if (connection != null) connection.close(); } catch (Exception e) {}
		}
	}

}
