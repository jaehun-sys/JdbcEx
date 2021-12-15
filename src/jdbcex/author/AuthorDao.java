package jdbcex.author;

import java.util.ArrayList;

public interface AuthorDao {
	public ArrayList<AuthorVo> select();
	public boolean insert(AuthorVo vo);
	public boolean update(AuthorVo vo);
	public boolean delete(int num);
}
