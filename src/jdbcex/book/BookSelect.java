package jdbcex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelect {
	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb12");
			
		// 3. SQL문 준비 / 바인딩 / 실행 
			String sql = "SELECT * FROM BOOK ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
		// 4.결과처리
			while(rs.next()) {
				int BOOK_ID = rs.getInt("BOOK_ID");
				String TITLE = rs.getString("TITLE");
				String PUBS = rs.getString("PUBS");
				String PUB_DATE = rs.getString("PUB_DATE");
				String AUTHOR_ID = rs.getString("AUTHOR_ID");
				System.out.println(BOOK_ID + "\t" + TITLE + "\t" + PUBS + "\t"
									+ PUB_DATE + "\t" + AUTHOR_ID);
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

	}
}
