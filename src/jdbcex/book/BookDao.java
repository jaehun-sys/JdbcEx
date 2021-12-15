package jdbcex.book;

import java.util.ArrayList;

public interface BookDao {
	public ArrayList<BookVo> select();
	public boolean insert(BookVo vo);
	public boolean update(BookVo vo);
	public boolean delete(int num);
}
