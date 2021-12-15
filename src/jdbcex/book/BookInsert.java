package jdbcex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInsert {
	public static void main(String[] args) {
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
			sql.append("INSERT INTO BOOK(BOOK_ID, TITLE, PUBS, PUB_DATE, AUTHOR_ID) ");
			sql.append("VALUES(SEQ_BOOK_ID.NEXTVAL, ?, ?, ?, ?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int index = 1;
			pstmt.setString(index++, "나미야 잡화점의 기적");
			pstmt.setString(index++, "현대문학");
			pstmt.setString(index++, "2012-12-19");
			pstmt.setString(index++, "6");
			
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
