package jdbcex.bookmanager.dao;

import java.util.ArrayList;

import jdbcex.bookmanager.vo.BookAuthor;

public interface BookManagerDao {
	public ArrayList<BookAuthor> select();
	public ArrayList<BookAuthor> selectByAuthor(String author);
}
