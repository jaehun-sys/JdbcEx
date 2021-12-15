package jdbcex.author;

public class AuthorVo {
	private int author_id;
	private String author_name;
	private String author_desc;
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getAuthor_desc() {
		return author_desc;
	}
	public void setAuthor_desc(String author_desc) {
		this.author_desc = author_desc;
	}
	
	public String to_String() {
        return "AuthorVo [author_id=" + author_id + ", author_name=" + author_name + ", author_desc=" + author_desc + "]";
    }
}
