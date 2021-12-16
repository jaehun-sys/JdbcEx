package jdbcex.bookmanager;

import java.util.ArrayList;
import java.util.Scanner;

import jdbcex.author.AuthorDao;
import jdbcex.author.AuthorDaoImpl;
import jdbcex.author.AuthorVo;
import jdbcex.book.BookDao;
import jdbcex.book.BookDaoImpl;
import jdbcex.book.BookVo;
import jdbcex.bookmanager.dao.BookManagerDao;
import jdbcex.bookmanager.vo.BookAuthor;

public class BookManagerApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean power = true;
		
		while(power) {
			System.out.println("================ BookManager 프로그램입니다 ================");
			System.out.println("1.저자 정보	2.책 정보		3.종료");
			System.out.print("> ");
			int startInput = sc.nextInt();
			BookManagerDao dao = new BookManagerDaoImpl();
			switch(startInput) {
			case 1:				//저자 정보
				ArrayList<BookAuthor> list = dao.select();
				ArrayList<String> list2 = new ArrayList<>();
//				int count = 0;
				boolean authorOn = true;
				while(authorOn) {
					System.out.println("================ 저자 정보 메뉴입니다 ================");
					for(int i=0; i<list.size(); i++) {
						if(!list2.contains(list.get(i).getAuthor_name())) {			
							list2.add(list.get(i).getAuthor_name());
//						count++;
						}
					}
					
					for(int i=0; i<list2.size(); i++) {					//저자 리스트 출력
						System.out.println(i+1 + ". " + list2.get(i));
					}
					System.out.println(list2.size()+1 + ". * 저자 추가");
					System.out.println(list2.size()+2 + ". * 돌아가기");
					
					System.out.print("> ");								//inputAuthor!!!!
					int inputAuthor = sc.nextInt();
					if(inputAuthor < 1 || inputAuthor > list2.size()+1) {			//오류
						System.out.println("잘못된 입력입니다");
						break;
					}else if(inputAuthor == list2.size()+1) {						//저자 추가
						System.out.println("================ 저자 추가 ================");
						//System.out.println(inputAuthor + " 저자 추가");
						System.out.println("저자 정보를 입력하세요.");
						AuthorDao adao = new AuthorDaoImpl();
						System.out.print("저자 이름: ");
						String inputAuthorName = sc.next();
						sc.nextLine();
						System.out.print("저자 상세: ");
						String inputAuthorDesc = sc.nextLine();
						
						AuthorVo avo = new AuthorVo();
						avo.setAuthor_name(inputAuthorName);
						avo.setAuthor_desc(inputAuthorDesc);
						adao.insert(avo);
						break;
						
					}else if(inputAuthor == list2.size()+2){						//돌아가기
						break;
					}else {												//저자 아무나 선택을 하면
						ArrayList<BookAuthor> authorList = dao.selectByAuthor(list2.get(inputAuthor-1));
						while(true) {					
							System.out.println("================ "+authorList.get(0).getAuthor_name()+" ================");
							System.out.println(0 + ". * 저자수정");
							for(int i=0; i<authorList.size(); i++) {
								System.out.println(i+1 + ". " + authorList.get(i).getTitle());
							}
							System.out.println(authorList.size()+1 + ". * 책추가");
							System.out.println(authorList.size()+2 + ". * 돌아가기");
							
							System.out.print("> ");
							int inputAuthor2 = sc.nextInt();					//inputAuthor2!!!!
							//저자 수정
							if(inputAuthor2 < 0 || inputAuthor2 > authorList.size()+2){
								System.out.println("잘못된 입력입니다.");
								continue;
							}
							else if(inputAuthor2 == 0) {							
								System.out.println("수정 사항을 입력하세요.");
								AuthorDao adao = new AuthorDaoImpl();
								System.out.print("저자 이름: ");
								String inputAuthorName = sc.next();
								sc.nextLine();
								System.out.print("저자 상세: ");
								String inputAuthorDesc = sc.nextLine();
								
								AuthorVo avo = new AuthorVo();
								avo.setAuthor_name(inputAuthorName);
								avo.setAuthor_desc(inputAuthorDesc);
								//System.out.println((dao.selectByAuthor(list2.get(input-1))).get(0).getAuthor_id()+ " 가 id 맞어?");
								avo.setAuthor_id((dao.selectByAuthor(list2.get(inputAuthor-1))).get(0).getAuthor_id());
								adao.update(avo);
								break;
								
							}//책 추가
							else if(inputAuthor2 == authorList.size()+1) {	
								System.out.println("================ 책 추가 ================");
								System.out.println("책 정보를 입력하세요.");
								BookDao bdao = new BookDaoImpl();
								System.out.print("제목: ");
								String inputTitle = sc.next();
								sc.nextLine();
								System.out.print("출판사: ");
								String inputPubs = sc.next();
								sc.nextLine();
								System.out.print("출판일: ");
								String inputPubDate = sc.next();
								
								BookVo bvo = new BookVo();
								bvo.setTitle(inputTitle);
								bvo.setPubs(inputPubs);
								bvo.setPub_date(inputPubDate);
								bvo.setAuthor_id((dao.selectByAuthor(list2.get(inputAuthor-1))).get(0).getAuthor_id());
								bdao.insert(bvo);
								break;
							}else if(inputAuthor2 == authorList.size()+2){		//돌아가기
								break;
							}else {										//책 클릭하면
								while(true) {
									
									System.out.println("제목	- "+authorList.get(inputAuthor2-1).getTitle());
									System.out.println("책코드	- "+authorList.get(inputAuthor2-1).getBook_id());
									System.out.println("출판사	- "+authorList.get(inputAuthor2-1).getPubs());
									System.out.println("출판일	- "+authorList.get(inputAuthor2-1).getPub_date());
									System.out.println("저자	- "+authorList.get(inputAuthor2-1).getAuthor_name());
									System.out.println("저자코드	- "+authorList.get(inputAuthor2-1).getAuthor_id());
									System.out.println("0. * 돌아가기");
									System.out.print("> ");
									int inputBook2 = sc.nextInt();
									if(inputBook2==0) {
										break;
									}else {
										System.out.println("잘못된 입력입니다.");
										continue;
									}
								}
							}
						}
					}
				}
				
				break;
			
			
			
			case 2:				//책 정보
				ArrayList<BookAuthor> list3 = dao.select();	//Book은 중복되지 않지
				boolean bookOn = true;
				while(bookOn) {					
					System.out.println("================ 책 정보 메뉴입니다 ================");
					for(int i=0; i<list3.size(); i++) {
						System.out.println(i+1 + ". " + list3.get(i).getTitle());
					}
					System.out.println(list3.size()+1 + ". * 책 추가");
					System.out.println(list3.size()+2 + ". * 돌아가기");
					
					System.out.print("> ");								//inputBook!!!!
					int inputBook = sc.nextInt();
					if(inputBook < 1 || inputBook > list3.size()+1) {			//오류
						System.out.println("잘못된 입력입니다");
						break;
					}else if(inputBook == list3.size()+1) {						//책 추가
						System.out.println("================ 책 추가 ================");
						//System.out.println(inputBook + " 책 추가");
						System.out.println("책 정보를 입력하세요.");
						BookDao bdao = new BookDaoImpl();
						System.out.print("책 제목: ");
						String inputTitle = sc.next();
						sc.nextLine();
						System.out.print("출판사: ");
						String inputPubs = sc.next();
						sc.nextLine();
						System.out.print("출판일: ");
						String inputPub_date = sc.next();
						sc.nextLine();
						System.out.print("저자: ");
						String inputAuthorName = sc.nextLine();
						
						BookVo bvo = new BookVo();
						bvo.setTitle(inputTitle);
						bvo.setPubs(inputPubs);
						bvo.setPub_date(inputPub_date);
						
						
						for(AuthorVo alist : (new AuthorDaoImpl()).select()){
							if(alist.getAuthor_name().equals(inputAuthorName)) {
								bvo.setAuthor_id(alist.getAuthor_id());
								break;
							}
							else {
								bvo.setAuthor_id(-1);
							}
						}
						if(bvo.getAuthor_id()==-1) {
							System.out.println("그런 저자 없습니다.");
							continue;
						}
						
						
						bdao.insert(bvo);
						break;
						
					}else if(inputBook == list3.size()+2){						//돌아가기
						break;
					}else {		
						//책 아무거나 선택을 하면
						while(true) {							
							System.out.println("제목	- "+dao.select().get(inputBook-1).getTitle());
							System.out.println("코드	- "+dao.select().get(inputBook-1).getBook_id());
							System.out.println("출판사	- "+dao.select().get(inputBook-1).getPubs());
							System.out.println("출판일	- "+dao.select().get(inputBook-1).getPub_date());
							System.out.println("저자	- "+dao.select().get(inputBook-1).getAuthor_name());
							System.out.println("0. * 책 수정");
							System.out.println("1. * 돌아가기");
							System.out.print("> ");
							int inputBook2 = sc.nextInt();
							if(inputBook2==0) {					//책 수정
								System.out.println("================ 책 수정 ================");
								System.out.println("수정 사항을 입력하세요.");
								BookDao bdao = new BookDaoImpl();
								System.out.print("책 이름: ");
								String inputTitle = sc.next();
								sc.nextLine();
								System.out.print("출판사: ");
								String inputPubs = sc.next();
								sc.nextLine();
								System.out.print("출판일: ");
								String inputPub_date = sc.nextLine();
								
								BookVo bvo = new BookVo();
								bvo.setTitle(inputTitle);
								bvo.setPubs(inputPubs);
								bvo.setPub_date(inputPub_date);
								//System.out.println("얘 맞아? 북아이디: " + dao.select().get(inputBook-1).getBook_id());
								bvo.setBook_id(dao.select().get(inputBook-1).getBook_id());
								
							
								bdao.update(bvo);
								break;
							}else if(inputBook2==1) {		//돌아가기
								break;
							}else {
								System.out.println("잘못된 입력입니다.");
								continue;
							}
						}
						
					}
				}
				
				break;
				
				
				
			case 3:				//종료
				power = false;
				System.out.println("Bye~~~");
				break;
			default:
				System.out.println("잘못된 입력입니다");
				break;
			}
			
			
			
			
		}
		sc.close();
	}
}
