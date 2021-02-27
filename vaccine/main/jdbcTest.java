package kr.or.connect.vaccine.main;

import java.text.SimpleDateFormat;
import java.sql.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.vaccine.config.ApplicationConfig;
import kr.or.connect.vaccine.dao.MemberDao;
import kr.or.connect.vaccine.dto.Member;



public class jdbcTest {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		MemberDao memberDao= ac.getBean(MemberDao.class);
		
		Member member =new Member();
		member.setMemberAge("61");
		member.setMemberSex("남성");
		member.setMemberVac("6");
		
		//날짜 넣기
		SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd");
		String temp=format1.format(new java.util.Date());
		java.sql.Date now=java.sql.Date.valueOf(temp);
		member.setMemberDate(now);
		
		int count=memberDao.insert(member);
		System.out.println(count+"건 입력하였습니다.");
//		int count=memberDao.update(member);
//		System.out.println(count+"건 수정하였습니다.");
		//업데이트 시에는 setMemberID도 넣어주어야 한다.
//		
//		Member result=memberDao.selectById(502);
//		System.out.println(result);
//		
//		int deleteCount=memberDao.deleteById(500);
//		System.out.println(deleteCount+"건 삭제하였습니다.");
		//삭제시 들어갈 값은 id값
	}

}
