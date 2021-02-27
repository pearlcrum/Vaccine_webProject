package kr.or.connect.vaccine.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.vaccine.config.ApplicationConfig;
import kr.or.connect.vaccine.dto.Member;



public class MemberDaoTest {

	public static void main(String[] args) {

		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		MemberDao memberDao = ac.getBean(MemberDao.class);
		
//		Member member = new Member();
//		member.setMemberAge("67");
//		member.setMemberSex("여성");
//		member.setMemberVac(7);
//		member.setMemberDate(new Date());
//		int id=memberDao.insert(member);
//		System.out.println("id: " + id);

		
//		int count=memberDao.selectCount();
//		System.out.println(count);
//		int del=memberDao.deleteById(14);
//		System.out.println(del);
		int count=memberDao.selectCountWithoutVac("독감","2020-01-01","2021-02-18");
		System.out.println(count);
		System.out.println("----------------------------");
		
		System.out.println("----------------------------");
		List<Member> theallbypage= new ArrayList<>();
		theallbypage=memberDao.selectAllWithoutVac("독감","2020-01-01","2021-02-18",0,4);
		for(Member member : theallbypage)
		{
			System.out.println(member);
		}
//		int count=memberDao.selectCountBySQL("member_sex=남", "2020-01-01", "2021-02-18");
//		System.out.println(count);
//		
		
	}

}
