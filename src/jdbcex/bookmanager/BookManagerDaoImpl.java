package jdbcex.bookmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbcex.bookmanager.dao.BookManagerDao;
import jdbcex.bookmanager.vo.BookAuthor;

public class BookManagerDaoImpl implements BookManagerDao{

	@Override
	public ArrayList<BookAuthor> select() {
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		ArrayList<BookAuthor> list = new ArrayList<>();
		
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb12");
			
		// 3. SQL문 준비 / 바인딩 / 실행 
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT	B.BOOK_ID							AS book_id	, ");
			sql.append("		B.TITLE								AS title	, ");
			sql.append("		B.PUBS								AS pubs		, ");
			sql.append("		TO_CHAR(B.PUB_DATE, 'YYYY-MM-DD')	AS pub_date	, ");
			sql.append("		A.AUTHOR_NAME						AS author_name ");
			sql.append("FROM 	BOOK	B, ");
			sql.append("		AUTHOR	A  ");
			sql.append("WHERE 	B.AUTHOR_ID = A.AUTHOR_ID ");
			
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
		// 4.결과처리
			while(rs.next()) {
				//VO 빈 객체 생성
				BookAuthor vo = new BookAuthor();
				
				//조회된 내용을 vo 객체에 세팅
				int book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pub_date = rs.getString("pub_date");
				String author_name = rs.getString("author_name");
				vo.setBook_id(book_id);
				vo.setTitle(title);
				vo.setPubs(pubs);
				vo.setPub_date(pub_date);
				vo.setAuthor_name(author_name);
				
				//세팅이 끝난 vo를 list에 담기
				list.add(vo);
				
				//System.out.println(book_id + "\t" + title + "\t" + pubs + "\t" + pub_date + "\t" + author_name);
			}
		} catch (ClassNotFoundException e) { 
			System.err.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) { 
			System.err.println("error:" + e);
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
				System.err.println("error:" + e); 
			}
		}
		return list;
	}

	@Override
	public ArrayList<BookAuthor> selectByAuthor(String author) {
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		ArrayList<BookAuthor> list = new ArrayList<>();
		
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb12");
			
		// 3. SQL문 준비 / 바인딩 / 실행 
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT	B.TITLE								AS title    , ");
			sql.append("		B.BOOK_ID							AS book_id	, ");
			sql.append("		B.PUBS								AS pubs		, ");
			sql.append("		TO_CHAR(B.PUB_DATE, 'YYYY-MM-DD')	AS pub_date	, ");
			sql.append("		A.AUTHOR_NAME						AS author_name, ");
			sql.append("		A.AUTHOR_ID							AS author_id ");
			
			sql.append("FROM 	BOOK	B, ");
			sql.append("		AUTHOR	A  ");
			sql.append("WHERE 	B.AUTHOR_ID = A.AUTHOR_ID ");
			sql.append("AND  	A.AUTHOR_NAME = ? ");
			
			
			pstmt = conn.prepareStatement(sql.toString());
			
			
			int index = 1;
			pstmt.setString(index++, author);
			
			rs = pstmt.executeQuery();
			
		// 4.결과처리
			while(rs.next()) {
				//VO 빈 객체 생성
				BookAuthor vo = new BookAuthor();
				
				//조회된 내용을 vo 객체에 세팅
				String title = rs.getString("title");
				int book_id = rs.getInt("book_id");
				String pubs = rs.getString("pubs");
				String pub_date = rs.getString("pub_date");
				String author_name = rs.getString("author_name");
				int author_id = rs.getInt("author_id");
				vo.setTitle(title);
				vo.setBook_id(book_id);
				vo.setPubs(pubs);
				vo.setPub_date(pub_date);
				vo.setAuthor_name(author_name);
				vo.setAuthor_id(author_id);
				//세팅이 끝난 vo를 list에 담기
				list.add(vo);
				
				//System.out.println(book_id + "\t" + title + "\t" + pubs + "\t" + pub_date + "\t" + author_name);
			}
		} catch (ClassNotFoundException e) { 
			System.err.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) { 
			System.err.println("error:" + e);
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
				System.err.println("error:" + e); 
			}
		}
		return list;
	}

//	@Override
//	public ArrayList<BookAuthor> selectAuthor() {
//		// TODO Auto-generated method stub
//		Connection conn = null; 
//		PreparedStatement pstmt = null; 
//		ResultSet rs = null;
//		ArrayList<BookAuthor> list = new ArrayList<>();
//		
//		try {
//		// 1. JDBC 드라이버 (Oracle) 로딩 
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//		// 2. Connection 얻어오기
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			conn = DriverManager.getConnection(url, "webdb", "webdb12");
//			
//		// 3. SQL문 준비 / 바인딩 / 실행 
//			StringBuilder sql = new StringBuilder();
//			sql.append("SELECT	A.AUTHOR_NAME						AS author_name ");
//			sql.append("FROM 	AUTHOR	A");
//			
//			pstmt = conn.prepareStatement(sql.toString());
//			rs = pstmt.executeQuery();
//			
//		// 4.결과처리
//			while(rs.next()) {
//				//VO 빈 객체 생성
//				BookAuthor vo = new BookAuthor();
//				
//				//조회된 내용을 vo 객체에 세팅
//				String author_name = rs.getString("author_name");
//		
//				vo.setAuthor_name(author_name);
//				
//				//세팅이 끝난 vo를 list에 담기
//				list.add(vo);
//				
//				System.out.println(author_name);
//			}
//		} catch (ClassNotFoundException e) { 
//			System.out.println("error: 드라이버 로딩 실패 - " + e);
//		} catch (SQLException e) { 
//			System.out.println("error:" + e);
//		} finally {
//		// 5. 자원정리
//			try {
//			if (rs != null) {
//				rs.close(); 
//			}
//			if (pstmt != null) { 
//				pstmt.close();
//			} 
//			if (conn != null) {
//				conn.close(); 
//			}
//			} catch (SQLException e) {
//				System.out.println("error:" + e); 
//			}
//		}
//		return list;
//	}
	

}
