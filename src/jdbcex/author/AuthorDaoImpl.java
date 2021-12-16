package jdbcex.author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorDaoImpl implements AuthorDao{
	@Override
	public ArrayList<AuthorVo> select() {
		// 0. import java.sql.*;
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		ArrayList<AuthorVo> list = new ArrayList<>();
		
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb12");
			
		// 3. SQL문 준비 / 바인딩 / 실행 
			String sql = "select * from author";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
		// 4.결과처리
			while(rs.next()) {
				//VO 빈 객체 생성
				AuthorVo vo = new AuthorVo();
				
				//조회된 내용을 vo 객체에 세팅
				int author_id = rs.getInt("author_id");
				String author_name = rs.getString("author_name");
				String author_desc = rs.getString("author_desc");
				vo.setAuthor_id(author_id);
				vo.setAuthor_name(author_name);
				vo.setAuthor_desc(author_desc);
				
				//세팅이 끝난 vo를 list에 담기
				list.add(vo);
				
				//System.out.println(author_id + "\t" + author_name + "\t" + author_desc + "\t");
			}
		} catch (ClassNotFoundException e) { 
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) { 
			System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
			try {
			if (rs != null) {
				rs.close(); 
			}
			if (pstmt != null) { 
				pstmt.close();
			} 
			if (conn != null) {
				conn.close(); 
			}
			} catch (SQLException e) {
				System.out.println("error:" + e); 
			}
		}
		return list;

	}

	@Override
	public boolean insert(AuthorVo vo) {
		// TODO Auto-generated method stub
		boolean success = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb12");
			
		// 3. SQL문 준비 / 바인딩 / 실행 
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO AUTHOR(AUTHOR_ID, AUTHOR_NAME, AUTHOR_DESC) ");
			sql.append("VALUES(SEQ_AUTHOR_ID.NEXTVAL, ?, ?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int index = 1;
			pstmt.setString(index++, vo.getAuthor_name());
			pstmt.setString(index++, vo.getAuthor_desc());
			
			//실행 결과 리턴. sql 문장 실행 후, 변경된 row 수 int 타입으로 리턴
			int r = pstmt.executeUpdate();
			//pstmt.executeQuery() : select
			//pstmt.executeUpdate() : insert, update, delete
			System.out.println(r + "건 처리");
			
			success = true;
			
		}catch(SQLException e){
			System.err.println("SQL 에러: " + e.getMessage());
		}catch(ClassNotFoundException e) {
			System.err.println("JDBC Connector Driver 에러: " + e.getMessage());
		}finally{
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return success;
	}

	@Override
	public boolean update(AuthorVo vo) {
		boolean success = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb12");
			
		// 3. SQL문 준비 / 바인딩 / 실행 
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE AUTHOR " );
			sql.append("SET AUTHOR_NAME = ?, ");
			sql.append("AUTHOR_DESC = ? ");
			sql.append("WHERE AUTHOR_ID = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int index = 1;
			pstmt.setString(index++, vo.getAuthor_name());
			pstmt.setString(index++, vo.getAuthor_desc());
			pstmt.setInt(index++, vo.getAuthor_id());
			
			//실행 결과 리턴. sql 문장 실행 후, 변경된 row 수 int 타입으로 리턴
			int r = pstmt.executeUpdate();
			//pstmt.executeQuery() : select
			//pstmt.executeUpdate() : insert, update, delete
			System.out.println(r + "건 처리");
			
			success = true;
			
		}catch(SQLException e){
			System.err.println("SQL 에러: " + e.getMessage());
		}catch(ClassNotFoundException e) {
			System.err.println("JDBC Connector Driver 에러: " + e.getMessage());
		}finally{
			//자원 정리
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return success;
	}

	@Override
	public boolean delete(int num) {
		boolean success = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb12");
			
		// 3. SQL문 준비 / 바인딩 / 실행 
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM AUTHOR " );
			sql.append("WHERE AUTHOR_ID = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int index = 1;
			pstmt.setInt(index++, num);
			
			//실행 결과 리턴. sql 문장 실행 후, 변경된 row 수 int 타입으로 리턴
			int r = pstmt.executeUpdate();
			//pstmt.executeQuery() : select
			//pstmt.executeUpdate() : insert, update, delete
			
			System.out.println(r + "건 처리");
			success = true;
			
		}catch(SQLException e){
			System.err.println("SQL 에러: " + e.getMessage());
		}catch(ClassNotFoundException e) {
			System.err.println("JDBC Connector Driver 에러: " + e.getMessage());
		}finally{
			//자원 정리
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return success;
	}
}
