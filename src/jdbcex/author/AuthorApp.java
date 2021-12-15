package jdbcex.author;

public class AuthorApp {
	public static void main(String[] args) {
		// 인터페이스 레퍼런스 변수에 구현크래스의 객체 만들기
		AuthorDao dao = new AuthorDaoImpl();

		// select()
		System.out.println("--select--");
		dao.select();
//		ArrayList<AuthorVo> list = dao.select();
//		for (AuthorVo vo : list) { System.out.println(vo.to_String()); }
		
		
		AuthorVo vo = new AuthorVo();
		// insert()
		System.out.println("--insert--"); 
		vo.setAuthor_name("작가2");
		vo.setAuthor_desc("작가2의 상세정보"); 
		dao.insert(vo);
		dao.select();

		//update() 
		System.out.println("--update--"); 
		vo.setAuthor_name("수정 작가3");
		vo.setAuthor_desc("수정 작가3의 상세정보"); 
		vo.setAuthor_id(6); 
		dao.update(vo);
		dao.select();
		
		//delete() 
		System.out.println("--delete--"); 
		dao.delete(9);
		dao.select();
		
	}
}
