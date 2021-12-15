package jdbcex.bookmanager;

import java.util.ArrayList;
import java.util.Scanner;

import jdbcex.bookmanager.dao.BookManagerDao;
import jdbcex.bookmanager.vo.BookAuthor;

public class BookManagerApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean power = true;
		
		while(power) {
			BookManagerDao dao = new BookManagerDaoImpl();
			ArrayList<BookAuthor> list = dao.select();
			ArrayList<String> list2 = new ArrayList<>();
			int count = 0;
			
			for(int i=0; i<list.size(); i++) {
				if(!list2.contains(list.get(i).getAuthor_name())) {			
					list2.add(list.get(i).getAuthor_name());
					count++;
				}else {
					count--;
				}
			}
			
			for(int i=0; i<list2.size(); i++) {
				System.out.println(i+1 + ". " + list2.get(i));
			}
			System.out.println(list2.size()+1 + ". 저자 추가");

			System.out.print("> ");								//input!!!!
			int input = sc.nextInt();
			if(input < 1 || input > count+2) {
				System.out.println("일단 나가 오류야");
				power = false;
			}else if(input == count+2) {
				System.out.println(input + " 저자 추가");
				
			}else {												//저자 아무나 선택을 하면
				ArrayList<BookAuthor> authorList = dao.selectByAuthor(list2.get(input-1));
				boolean authorOn = true;
				while(authorOn) {					
					System.out.println(0 + ". 저자수정");
					for(int i=0; i<authorList.size(); i++) {
						System.out.println(i+1 + ". " + authorList.get(i).getTitle());
					}
					System.out.println(authorList.size()+1 + ". 책추가");
					
					System.out.print("> ");
					int input2 = sc.nextInt();					//input2!!!!
					
				}
			}
			
			
		}
	}
}
