package jdbcex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookUpdate {
	public static void main(String[] args) {
		//boolean success = false;
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
			sql.append("UPDATE BOOK " );
			sql.append("SET TITLE = ?, ");
			sql.append("PUBS = ?, ");
			sql.append("PUB_DATE = ? ");
			sql.append("WHERE BOOK_ID = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int index = 1;
			pstmt.setString(index++, "삼국지");
			pstmt.setString(index++, "몰라요");
			pstmt.setString(index++, "1988-11-20");
			pstmt.setString(index++, "4");
			
			//실행 결과 리턴. sql 문장 실행 후, 변경된 row 수 int 타입으로 리턴
			//int r = pstmt.executeUpdate();
			//pstmt.executeQuery() : select
			//pstmt.executeUpdate() : insert, update, delete
			
			pstmt.executeUpdate();
		// 4. 결과처리
			
			
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
	}
}
