package jdbcex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindBookInfo {

	public static void main(String[] args) {
		
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
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT	B.TITLE,		");
			sql.append("		A.AUTHOR_NAME	");
			sql.append("FROM 	BOOK	B,		");
			sql.append("		AUTHOR	A		");
			sql.append("WHERE 	B.AUTHOR_ID = A.AUTHOR_ID ");
			
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
		// 4.결과처리
			while(rs.next()) {
				String TITLE = rs.getString("TITLE");
				String AUTHOR_NAME = rs.getString("AUTHOR_NAME");
				System.out.printf("%-9s\t%s \n", TITLE, AUTHOR_NAME);
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
